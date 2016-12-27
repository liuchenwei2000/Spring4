package httprpc.server;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置 Hessian 服务
 * <p>
 *     因为 Hessian 是基于 HTTP 的，并且 HessianServiceExporter 是一个 SpringMVC 控制器。
 *     这意味着为了使用导出的 Hessian 服务，需要执行两个额外的配置：
 *     <li>1，配置 DispatcherServlet，并把应用部署为 Web 应用。
 *     <li>2，在 Spring 中配置一个 URL 处理器，把 Hessian 服务的 URL 分发给对应的 Hessian 服务 bean。
 *     <p>
 *     Burlap 和 HttpInvoker 与 Hessian 的配置方式类似。
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
public class HttpRpcWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        // 1，任何以 ".service" 结束的 URL 请求都将由 DispatcherServlet 处理，
        // 它会把请求传递给匹配这个 URL 的控制器，因此 "/book.service" 的请求
        // 最终将被 hessianBookService bean 所处理（它实际上仅仅是一个 BookServiceImpl 的代理）。
        return new String[]{"/", "*.service"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }
}
