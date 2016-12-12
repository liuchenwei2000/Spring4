package datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * 数据源 Java 配置示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Configuration
public class DataSourceConfig {

    /**
     * 1，配置 JNDI 数据源
     */
    @Bean
    public JndiObjectFactoryBean dataSource1(){
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        jndiObjectFB.setJndiName("jdbc/MyDatasource");
        jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(DataSource.class);
        return jndiObjectFB;
    }

    /**
     * 2，使用数据源连接池
     */
    @Bean
    public BasicDataSource dataSource2() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");
        basicDataSource.setInitialSize(5);// 池启动时创建的连接数量
        basicDataSource.setMaxActive(10);// 同一时间池中的最大连接数
        return basicDataSource;
    }

    /**
     * 3，基于 JDBC 驱动的数据源
     */
    @Bean
    public DataSource dataSource3() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }
}
