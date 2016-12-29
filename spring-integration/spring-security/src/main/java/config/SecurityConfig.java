package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 2，安全性配置
 * <p>
 *     <code>@EnableWebSecurity</code> 注解将会启用 Web 安全功能，但它本身并没有什么用处，
 *     Spring Security 必须配置在一个实现了 WebSecurityConfigurer 的 bean 中，
 *     最简单的方式是继承 WebSecurityConfigurerAdapter。在 Spring 上下文中，
 *     任何实现了 WebSecurityConfigurer 的 bean 都可以用来配置 Spring Security。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
