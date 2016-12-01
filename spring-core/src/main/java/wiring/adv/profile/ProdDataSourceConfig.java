package wiring.adv.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * 生产环境 DataSource 配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@Configuration
public class ProdDataSourceConfig {

    // 可以在方法级别上使用 @Profile 注解，与 @Bean 注解一同使用。
    // 这样的话，就能将这两个 bean 的声明放到同一个配置类中。
    @Bean("dataSource")
    @Profile("test")
    public DataSource testDataSource(){
        // 返回一个测试环境使用的 DataSource 对象
        return new MockDataSource("test");
    }

    @Bean("dataSource")
    @Profile("prod")
    public DataSource prodDataSource(){
        // 返回一个测试环境使用的 DataSource 对象
        return new MockDataSource("production");
    }

    // 没有指定 profile 的 bean 始终都会被创建，与激活哪个 profile 没有关系。
    @Bean
    public String common(){
        return "common";
    }
}
