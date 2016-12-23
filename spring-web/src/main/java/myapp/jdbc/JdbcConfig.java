package myapp.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Jdbc 模板配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/23.
 */
@Configuration
@PropertySource("classpath:app.properties")// 从 properties 文件中加载配置信息
public class JdbcConfig {

    @Autowired
    private Environment env;

    /**
     * 配置 JdbcTemplate
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 配置数据源
     */
    @Bean
    public DataSource dataSource() {
        String driver = env.getProperty("ds.driver");
        String url = env.getProperty("ds.url");
        String userid = env.getProperty("ds.userid");
        String password = env.getProperty("ds.password");

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userid);
        ds.setPassword(password);
        return ds;
    }
}
