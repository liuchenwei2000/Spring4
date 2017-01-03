package myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

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

    /**
     * 如果想要指定 Web 安全的细节，要通过重载下面三个 configure() 方法来实现。
     * 这个过程中会使用传递进来的参数设置行为。
     */

    /**
     * 配置 Spring Security 的 Filter 链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 配置如何通过拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        // 上面默认的配置实际上等同于如下代码
        http.authorizeRequests()
                .anyRequest().authenticated()// 所有应用的 HTTP 请求都要进行认证
                .and()
                .formLogin()// 支持基于表单的登录
                .and()
                .httpBasic();// 支持 HTTP Basic 方式的认证
    }

    @Autowired
    private DataSource dataSource;

    /**
     * 配置查询用户详细信息（user-detail） 服务
     * <p>
     *     用户存储就是用户名、密码以及其他信息存储的地方，在进行认证决策的时候，会对其进行检索。
     *     Spring Security 非常灵活，能够基于各种数据存储来认证用户。
     *     它内置了多种常见的用户存储场景，如内存、关系型数据库以及 LDAP。
     *     另外也可以编写并插入自定义的用户存储实现。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // AuthenticationManagerBuilder 有多个方法可以用来配置 Spring Security 对认证的支持：

        /** 1，使用基于内存的用户存储 */
        // 比如 inMemoryAuthentication() 方法可以启用、配置并任意填充基于内存的用户存储。
        auth.inMemoryAuthentication()
                // 为内存用户存储添加用户、配置密码并为其授予一个或多个角色权限
                .withUser("user").password("password").roles("USER")
                .and()
                // roles() 方法是 authorities() 方法的简写形式，roles() 方法所给定的值
                // 都会添加一个 "ROLE_" 前缀，并将其作为权限授予给用户。
                .withUser("admin").password("admin").roles("USER", "ADMIN");

        // 配置用户详细信息的其他方法
        // accountExpired(boolean):定义账号是否已经过期
        // accountLocked(boolean):定义账号是否已经锁定
        // disabled(boolean):定义账号是否已被禁用

        /**
         * 2，基于数据库表进行认证
         *
         * 对于调试和开发测试而言，基于内存的用户存储时很有用的，但是对于生产级别的应用而言，
         * 用户数据通常会存储在关系数据库中，并通过 JDBC 进行访问。
         */
        // 使用 jdbcAuthentication() 方法可以启用、配置基于数据库的用户存储
        auth.jdbcAuthentication().dataSource(dataSource)
                // 如果应用程序的数据库与 Spring Security 默认的库表结构不一致，则可以通过下面的方式配置自己的查询
                // 将默认的 SQL 查询替换为自定义的设计时，需要注意的是要遵循查询的基本协议：
                // 所有查询都将用户名作为唯一的参数，认证查询会选取用户名、密码以及启用状态信息，
                .usersByUsernameQuery("select username,password,true from myapp_user where username=?")
                // 权限查询会选取零行或多行包含该用户名及其权限信息的数据。
                .authoritiesByUsernameQuery("select username,'ROLE_USER' from myapp_role where username=?")
                // 上面的查询基于用户密码是以明文存储在数据库中，这很容易被黑客窃取。
                // 但是如果数据库中的密码进行了转码的话，那么认证查询就会失败，因为它与用户提交的明文密码并不匹配。
                // 为了解决这个问题，需要借助 passwordEncoder() 方法指定一个密码转码器（encoder）。
                // Spring Security 的加密模块包括了三个这样的实现：
                // BCryptPasswordEncoder、NoOpPasswordEncoder、StandardPasswordEncoder。
                .passwordEncoder(new StandardPasswordEncoder("wwe873"))
                // 如果内置的实现无法满足需要时，可以提供自定义的 PasswordEncoder 实现。
                // 不管使用哪一个密码转码器，都要理解数据库中的密码是永远不会解码的。
                // 与之相反的是，用户在登录时输入的密码会按照相同的算法进行转码，然后在与数据库中已经转码过的密码进行对比。
        ;
    }
}
