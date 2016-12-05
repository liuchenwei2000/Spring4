package aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 在 Java 配置中启用 AspectJ 注解的自动代理
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/5.
 */
@Configuration
@ComponentScan
// 启用 AspectJ 自动代理
@EnableAspectJAutoProxy
public class ConcertConfig {

    @Bean
    // 声明 Audience bean，因为有 @EnableAspectJAutoProxy 的存在，
    // Audience bean 就不再仅仅是一个普通的 bean，而会被视为切面
    public Audience audience(){
        return new Audience();
    }

    /**
     * 普通的一个 bean
     */
    @Bean
    public Performance concert(){
        return new Concert();
    }
}
