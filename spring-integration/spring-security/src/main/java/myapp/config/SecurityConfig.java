package myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
     * <p>
     *     在任何应用中，并不是所有的请求都需要同等程度地保护。有些请求需要认证，而另一些可能并不需要。
     *     有些请求可能只有具备特定权限的用户才能访问，没有这些权限的用户则无法访问。
     *     对每个请求进行细粒度安全性控制的关键在于重载 configure(HttpSecurity) 方法。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        // 上面默认的配置实际上等同于如下代码
//        configHttpDefault(http);

        // 对请求进行自定义的拦截配置
        configHttpCustom(http);

    }

    /**
     * Spring Security 对 HTTP 安全默认的配置方式
     */
    private void configHttpDefault(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()// 所有应用的 HTTP 请求都要进行认证
                .and()
                .formLogin()// 支持基于表单的登录，启用默认的登录页
                .and()
                .httpBasic();// 支持 HTTP Basic 方式的认证
    }

    /**
     * 自定义对 HTTP 请求的拦截方式
     */
    private void configHttpCustom(HttpSecurity http) throws Exception {
        // 禁用 CSRF 防护功能
//        http.csrf().disable();
        http.authorizeRequests()
                // 对 "/employee/me" 路径的请求进行认证，authenticated() 要求在执行该请求时必须已经登录。
                // 如果用户没有认证的话，Spring Security 的 Filter 将会捕获该请求，并将用户重定向到登录页面。
                .antMatchers("/employee/me").authenticated()

                // 对 "/employee/save" 路径的 POST 请求进行认证并具备 ROLE_ADMIN 权限
                .antMatchers(HttpMethod.POST,"/employee/save").hasRole("ADMIN")

                // antMatchers() 方法中设定的路径支持 Ant 风格的通配符，可以使用通配符来指定路径。
//                .antMatchers("/employee/**").authenticated()

                // 可以在 antMatchers() 方法中指定多个路径
//                .antMatchers("/employee/**", "/employee/mine").authenticated()

                // regexMatchers() 方法能够接受正则表达式来定义请求路径
//                .regexMatchers("/employee/.*").authenticated()

                // 对其他所有的请求（如对主页的 GET 请求）都是允许的，permitAll() 方法允许请求没有任何的安全限制
                .anyRequest().permitAll()

                // 除了 authenticated() 和 permitAll() 以外，还有其他的一些方法能够用来定义该如何保护请求
                // anonymous()：允许匿名用户访问
                // denyAll()：无条件拒绝所有访问
                // hasRole(String)：如果用户具备给定角色的话就允许访问
                // hasAnyRole(String...)：如果用户具备给定角色中的某一个的话就允许访问
                // hasIpAddress(String)：如果请求来自给定 IP 的话就允许访问

                /*
                * 可以将任意数量的 antMatchers()、regexMatchers() 和 anyRequest() 连接起来，
                * 以满足 Web 应用安全规则的需要。但是这些规则会按照给定的顺序发挥作用。
                * 所以，要将最为具体的请求路径放在前面，而最不具体的路径（如 anyRequest()）放在后面。
                * 如果不这样做的话，那不具体的路径配置将会覆盖掉更为具体的路径配置。
                */

                .and()
                /*
                * 通过 HTTP 发送的数据没有经过加密，黑客就有机会拦截请求并且能够看到隐秘数据，
                * 这就是为什么敏感信息要通过 HTTPS 来加密发送的原因。
                * 借助 requiresChannel() 方法能够为各种 URL 模式声明所要求的通道。
                *
                * 本例中只要是对 "/employee/register" 的请求，Spring Security 都视为
                * 需要安全通道（requiresChannel()确定的）并自动将请求重定向到 HTTPS 上。
                */
                .requiresChannel()
                .antMatchers("/employee/register").requiresSecure()
                // 有些页面并不需要通过 HTTPS 传送（如首页），可以使用 requiresInsecure()
                // 代替 requiresSecure() 方法将首页声明为始终通过 HTTP 传送。
                .antMatchers("/", "/index", "/home").requiresInsecure()

                .and()

                /*
                * 登录
                *
                * 使用 loginPage() 方法添加自定义的登录页，参数实际上是 URL 模式，
                * 可以由负责处理它的控制器返回登录页的逻辑视图名，参见 myapp.controller.LoginController
                */
                .formLogin().loginPage("/login")

                .and()

                /*
                * 启用 Remember-me 功能
                *
                * 对于用户而言，只要登录过一次，应用就应该能记住，当再次回到应用的时候就不需要再登录了。
                * 可以使用 rememberMe() 启用这个功能，默认情况下，这是通过在 cookie 中存储一个 token 完成的。
                * 这个 token 最多两周内有效，但是本例中指定了它一周内有效（60 * 60 * 24 * 7 秒）。
                * token 中包含用户名、密码、过期时间和一个私钥——在写入 cookie 前都进行了 MD5 哈希。
                * 默认情况下，私钥的名为 SpringSecured，在这里将其设置为 myappKey，使其专用于本应用。
                */
                .rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).key("myappKey")

                .and()

                /*
                * 退出
                *
                * 退出功能是通过 Spring Security 的 LogoutFilter 实现的，
                * 这个 Filter 会拦截所有对 "/logout" 的请求。
                * 所以默认情况下，只需要在应用的页面添加对此请求的超链接即可完成退出功能。
                * 当用户点击这个链接的时候，会发起对 "/logout" 的请求，LogoutFilter 会拦截该请求。
                * 用户会退出停用，所有的 Remember-me token 都会被清除掉。
                * 在退出完成后，浏览器将会重定向到 "/login?logout"，从而允许用户再次登录。
                * 也可以通过调用 logoutSuccessUrl() 指定退出成功后重定向的新页面 URL。
                */
                .logout().logoutSuccessUrl("/")// 退出成功后重定向到主页

                .and()

                /*
                * 启用 HTTP Basic 认证
                *
                * 对于应用程序的人类用户来说，基于表单的认证是比较理想的。
                * 但是当应用程序的使用者是另外一个应用程序的话，使用表单来提示登录的方式就不合适了。
                * HTTP Basic 认证会直接通过 HTTP 请求本身，对要访问应用程序的用户进行认证。
                * 当在 Web 浏览器中使用时，它将向用户弹出一个简单的模态对话框。
                * 但这只是 Web 浏览器的显示方式，本质上这是一个 HTTP 401 响应，
                * 表明必须要在请求中包含一个用户名和密码，在 REST 客户端向它使用的服务进行认证的场景中比较适合。
                * 另外，还可以通过调用 realmName() 方法指定域。
                */
                .httpBasic();
    }

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

//        configAuthMemory(auth);
//        configAuthDatabase(auth);
        configAuthCustomUserService(auth);
    }

    /**
     * 1，使用基于内存的用户存储
     */
    private void configAuthMemory(AuthenticationManagerBuilder auth) throws Exception {
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
    }

    @Autowired
    private DataSource dataSource;

    /**
     * 2，基于数据库表进行认证
     * <p>
     * 对于调试和开发测试而言，基于内存的用户存储时很有用的，但是对于生产级别的应用而言，
     * 用户数据通常会存储在关系数据库中，并通过 JDBC 进行访问。
     */
    private void configAuthDatabase(AuthenticationManagerBuilder auth) throws Exception {
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

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    /**
     * 3，配置自定义的用户服务
     * <p>
     * 需要认证的用户存储在非关系型数据库中，如 Redis 等，
     * 这种情况下需要提供一个自定义的 UserDetailService 接口实现。
     */
    private void configAuthCustomUserService(AuthenticationManagerBuilder auth) throws Exception {
        // 配置使用自定义的用户存储
        auth.userDetailsService(userDetailsServiceImpl);
    }
}
