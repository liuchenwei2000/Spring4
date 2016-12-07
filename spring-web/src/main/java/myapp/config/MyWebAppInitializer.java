package myapp.config;

import myapp.DispatcherServletFilter1;
import myapp.DispatcherServletFilter2;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

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

    /**
     * Spring MVC 配置的替代方案
     *
     * 1，自定义 DispatcherServlet 配置
     *
     * 继承 AbstractAnnotationConfigDispatcherServletInitializer 除了必须实现的三个方法外，
     * 还有很多其他方法可以进行重载，从而实现额外的配置，比如 customizeRegistration() 方法。
     *
     * 在 AbstractAnnotationConfigDispatcherServletInitializer 将
     * DispatcherServlet 注册到 Servlet 容器中之后，就会调用本方法，
     * 并将 Servlet 注册后得到的 ServletRegistration.Dynamic 对象传递进来。
     * 通过重载本方法，可以对 DispatcherServlet 进行额外的配置。如下：
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 设置 load-on-startup 优先级
        registration.setLoadOnStartup(1);
        // 设置初始化参数
        registration.setInitParameter("myparam", "Hello");

        /*
        * 处理 multipart 形式的数据
        *
        * 2，配置 multipart 解析器
        *
        * 设置对 multipart 的支持，并设定其他参数值，主要如下：
        *
        * location
        * 上传文件的临时存储目录，指定的是文件系统中的一个绝对目录；
        *
        * maxFileSize
        * 上传文件的最大容量（字节为单位），默认没有限制；
        *
        * maxRequestSize
        * 整个 multipart 请求的最大容量（字节为单位），不会关系有多少 part 以及每个 part 的大小，默认没有限制；
        *
        * fileSizeThreshold
        * 在上传过程中，文件大小的最大容量（字节为单位），达到该容量则将写入到临时目录。默认值为 0，即所有上传文件都写入到磁盘。
        *
        * 如果使用传统的 web.xml 来配置 MultipartConfigElement，详见 web.xml 文件示例。
        */

        // 将上传文件的临时存储目录设置在 "/tmp/uploads"
//        registration.setMultipartConfig(new MultipartConfigElement("/tmp/uploads"));

        // 将上传文件的临时存储目录设置在 "/tmp/uploads"，
        // 同时限制文件大小不超过 2MB，整个请求不超过 4MB，所有文件都要写入磁盘
        registration.setMultipartConfig(new MultipartConfigElement(
                "/tmp/uploads", 2 * 1024 * 1024, 4 * 1024 * 1024, 0));
    }

    /**
     * 本方法返回的 Filter 将只会映射到 DispatcherServlet 上，而不会影响其他 Servlet。
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new DispatcherServletFilter1(), new DispatcherServletFilter2()};
    }
}
