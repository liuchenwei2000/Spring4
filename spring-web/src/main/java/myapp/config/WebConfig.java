package myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring MVC 配置类
 * <p>
 *     创建最简单的 SpringMVC 配置就是一个带有 @EnableWebMvc 注解的类。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@EnableWebMvc
@ComponentScan("myapp.controller")// 启用组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置 JSP 视图解析器
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");// 前缀
        resolver.setSuffix(".jsp");// 后缀
        resolver.setExposeContextBeansAsAttributes(true);
        // home 将会解析为 /WEB-INF/views/home.jsp
        // books/detail 将会解析为 /WEB-INF/views/books/detail.jsp
        // 当逻辑视图名中包含斜线时，这个斜线也会带到资源的路径名中。
        return resolver;
    }


    /**
     * 配置静态资源的处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 要求 DispatcherServlet 将对静态资源的请求转发到 Servlet 容器中默认的 servlet 上，
        // 而不是使用 DispatcherServlet 本身来处理此类请求。
        configurer.enable();
    }
}
