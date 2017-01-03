package myapp.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 1，过滤 Web 请求
 * <p>
 *     Spring Security 借助一系列 Servlet Filter 来提供各种安全性功能，
 *     借助于 Spring 的小技巧，只需配置一个 Filter（DelegatingFilterProxy）即可。
 *     DelegatingFilterProxy 是一个特殊的 Servlet Filter，它只是将工作委托给一个
 *     Filter 实现类，这个实现类会作为一个 bean 注册在 Spring 应用的上下文中。
 * <p>
 *     传统 web.xml 方式配置 DelegatingFilterProxy 可见 WEB-INF/web.xml，
 *     本类实现了借助 JavaConfig 的方式进行配置，即继承 AbstractSecurityWebApplicationInitializer。
 *     后者实现了 WebApplicationInitializer，因此 Spring 会自动发现它并用它在 Web 容器中
 *     注册 DelegatingFilterProxy。不管采用哪种方式配置 DelegatingFilterProxy，
 *     它都会拦截发往应用中的请求，并将请求委托给 id 为 springSecurityFilterChain 的 bean。
 * <p>
 *     springSecurityFilterChain 是一个特殊的 Filter，也被称为 FilterChainProxy。
 *     它可以链接任意一个或多个其他的 Filter，而 Spring Security 依赖一系列 Servlet Filter 来提供不同的安全特性。
 *     但是应用不需要知道这些细节，因为不需要显式声明 springSecurityFilterChain 以及它所链接在一起的其他 Filter。
 *     当应用启用 Web 安全性的时候，会自动创建这些 Filter。
 * <p>
 * Created by liuchenwei on 2016/12/29.
 */
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {
}
