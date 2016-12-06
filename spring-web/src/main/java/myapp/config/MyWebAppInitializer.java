package myapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 使用 Java 配置 DispatcherServlet
 * <p>
 *     借助 Servlet3 规范和 Spring 3.1 的功能增强，通过本例方式配置
 *     DispatcherServlet 是传统 web.xml 方式的替代方案。（需要容器支持 Servlet3.0）
 *     扩展 AbstractAnnotationConfigDispatcherServletInitializer 的任意类
 *     都会自动配置 DispatcherServlet 和 Spring 应用上下文，
 *     Spring 应用上下文会位于应用程序的 Servlet 上下文之中。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 将一个或多个路径映射到 DispatcherServlet 上
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // 将 DispatcherServlet 映射到 /
    }

    /**
     * 两个应用上下文
     * <p>
     *     当 DispatcherServlet 启动的时候，它会创建 Spring 应用上下文，并加载配置文件或配置类中所声明的 bean。
     *     加载 Spring 应用上下文时，使用定义在 getServletConfigClasses() 方法中的 WebConfig 配置类中的 bean。
     *     但是在 Spring Web 应用中，通常还会有另外一个应用上下文，由 ContextLoaderListener 创建的。
     *     一般而言，DispatcherServlet 加载包含 Web 组件的 bean，如控制器、视图解析器以及处理器映射；
     *     而 ContextLoaderListener 要加载应用中的其他 bean，这些 bean 通常是驱动应用后端的中间层和数据层组件。
     * <p>
     *     AbstractAnnotationConfigDispatcherServletInitializer 会同时创建 DispatcherServlet 和 ContextLoaderListener。
     *     getServletConfigClasses() 方法返回的配置类将会用来定义 DispatcherServlet 应用上下文中的 bean；
     *     getRootConfigClasses() 方法返回的配置类将会用来配置 ContextLoaderListener 创建的应用上下文中的 bean。
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }
}
