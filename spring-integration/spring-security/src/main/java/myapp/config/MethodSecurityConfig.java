package myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 方法调用安全配置类
 * <p>
 *     如果要启用基于注解的方法安全性，需要在配置类上使用 @EnableGlobalMethodSecurity 注解。
 *     注解的 securedEnabled 属性为 true 的话，将会创建一个切点，
 *     这样的话，Spring Security 切面就会包装带有 @Secured 注解的方法。
 *     注解的 jsr250Enabled 属性为 true 的话，也会创建一个切点，
 *     这样的话，Spring Security 切面就会包装带有 @RolesAllowed 注解的方法。
 *     基本上 @Secured 注解 和 @RolesAllowed 注解在各个方面都是一致的，
 *     只不过前者是 Spring 特有的，后者是 JSR-250 定义的，这两者可以同时启用。
 *     注解的 prePostEnabled 属性为 true 则会启用方法调用前后的表达式驱动注解。
 * <p>
 * Created by liuchenwei on 2017/1/5.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
// 继承自 GlobalMethodSecurityConfiguration 类是能够为方法级别的安全性提供更精细的配置
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
