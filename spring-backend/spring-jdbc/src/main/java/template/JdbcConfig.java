package template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Jdbc 模板配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Configuration
@ComponentScan
public class JdbcConfig {

    /**
     * 配置 JdbcTemplate，只需要数据源
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 配置 NamedParameterJdbcTemplate，只需要数据源
     */
    @Bean
    public NamedParameterJdbcTemplate npJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test2");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }
}
