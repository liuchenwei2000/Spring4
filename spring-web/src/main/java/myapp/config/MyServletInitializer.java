package myapp.config;

import myapp.MyFilter;
import myapp.MyRequestListener;
import myapp.MyServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 自定义初始化器
 * <p>
 *     Spring MVC 配置的替代方案
 *
 *     2，添加其他的 Servlet 和 Filter
 *
 *     基于 Java 的初始化器（Initializer）的一个好处在于可以定义任意数量的初始化器类。
 *     如果想往 Web 容器中注册其他组件的话，只需创建一个新的初始化器就可以了。
 *     最简单的方式就是实现 WebApplicationInitializer 接口。
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class MyServletInitializer implements WebApplicationInitializer {

    /**
     * 实现本方法以便注册 Servlet、Filter 和 Listener
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 注册 Servlet
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        // 映射 Servlet
        myServlet.addMapping("/custom/**");

        // 注册 Filter
        FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
        // 映射 Filter
        myFilter.addMappingForUrlPatterns(null, false, "/custom/*");

        // 注册 Listener
        servletContext.addListener(MyRequestListener.class);
    }
}
