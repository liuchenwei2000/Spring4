package jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Spring 中集成 JPA Java 配置示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Configuration
@ComponentScan
public class JpaConfig {

    /**
     * 配置应用程序管理类型的 JPA
     * <p>
     *     对于应用程序管理类型的实体管理器工厂而言，它绝大部分配置信息来源于一个名为
     *     persistence.xml 的配置文件，这个文件必须位于 META-INF 目录下。
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        // 赋予 persistenceUnitName 属性的值就是 persistence.xml 中持久化单元的名称。
        emfb.setPersistenceUnitName("userPU");
        return emfb;
    }

    /**
     * 配置容器管理类型的 JPA
     * <p>
     *     当运行在容器中时，可以使用容器（本例是 Spring）提供的信息来生成 EntityManagerFactory。
     *     可以将数据源信息配置在 Spring 应用上下文中，而不是在 persistence.xml 中了。
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        // 从 Spring3.1 开始，可以直接设置 packagesToScan 属性，自动扫描指定包中带 @Entity 注解的类。
        // 因此，没有必要在 persistence.xml 文件中进行声明了。
        // 同时，因为 DataSource 也是注入进来的，也没必要在 persistence.xml 文件中配置数据库信息了。
        // 综上所述，persistence.xml 文件完全没有必要存在，所以推荐使用容器管理类型的 JPA。
        emfb.setPackagesToScan("jpa");
        return emfb;
    }

    /**
     * jpaVendorAdapter 用于指定所使用的是哪一个厂商的 JPA 实现，Spring 支持：
     * HibernateJpaVendorAdapter、OpenJpaVendorAdapter、EclipseLinkVendorAdapter 等。
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return adapter;
    }

    /**
     * 使用 Spring 配置数据源，而不再是 persistence.xml。
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test4");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }

    /**
     * 从 JNDI 获取实体管理器工厂
     * <p>
     *     如果应用程序部署在应用服务器中，EntityManagerFactory 可能已经创建好
     *     并且位于 JNDI 中等待查询使用，这种情况可以像下面示例的配置：
     */
    @Bean
    public JndiObjectFactoryBean jndiEntityManagerFactory() {
        JndiObjectFactoryBean jofb = new JndiObjectFactoryBean();
        jofb.setJndiName("jdbc/userDS");// 设置 jndi 名称
        return jofb;
    }
}
