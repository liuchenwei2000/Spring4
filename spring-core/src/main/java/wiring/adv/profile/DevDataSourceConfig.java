package wiring.adv.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * 开发环境 DataSource 配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@Configuration
// 可以使用 @Profile 注解指定某个 bean 属于哪一个 profile，
// 比如本例的 dataSource bean 属于 dev profile。
// 这意味着只有在 dev profile 激活时才会创建这个配置类中的 bean。
@Profile("dev")
public class DevDataSourceConfig {

    @Bean
    public DataSource dataSource(){
        // 返回一个开发环境使用的 DataSource 对象
        return new MockDataSource("development");
    }
}
