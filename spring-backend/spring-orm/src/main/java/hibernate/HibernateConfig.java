package hibernate;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring 中集成 Hibernate Java 配置示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Configuration
@ComponentScan
public class HibernateConfig {

    /**
     * 使用 Hibernate 所需的主要接口是 org.hibernate.Session。
     * Session 接口提供了基本的数据访问功能，如保存、更新、删除以及查询对象。
     * 通过 Session 接口，应用程序的 Repository 能够满足所有的持久化需求。
     * <p>
     * 获取 Hibernate Session 对象的标准方式是借助于 Hibernate SessionFactory 接口的实现类。
     * 除了一些其他的任务，SessionFactory 主要负责 Hibernate Session 的打开、关闭以及管理。
     * <p>
     * LocalSessionFactoryBean 能够支持基于 XML 的映射和基于注解的映射。
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 告诉 Spring 扫描一个或多个包以查找域类，这些类要通过注解的方式表明要使用 Hibernate 进行持久化。
        // 这些类可以使用的注解包括 JPA 的 @Entity 或 @MappedSuperclass 以及 Hibernate 的 @Entity。
        factoryBean.setPackagesToScan(new String[]{"hibernate"});
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
        factoryBean.setHibernateProperties(props);
        return factoryBean;
    }

    /**
     * 为了给不使用模板的 Repository 添加异常转换功能，需要配置如下的异常转换器 bean
     */
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test3");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }
}
