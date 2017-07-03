package myapp.config;

import myapp.WebConstant;
import myapp.interceptor.BaseUrlInterceptor;
import myapp.interceptor.TimeCostInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

/**
 * Spring MVC 配置类
 * <p>
 *     创建最简单的 SpringMVC 配置就是一个带有 @EnableWebMvc 注解的类。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@EnableWebMvc
@EnableScheduling
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
     * 添加视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 如果只是想建立 URL 和逻辑视图之间的映射，不经过 Controller 处理直接跳转到页面上，
        // 可以采用下面的便捷方式，它将对 /hello 的访问直接跳转到 hello.jsp 上。
        // 需要注意的是，对逻辑视图名的解析，依然需要 ViewResolver 来处理。
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/sse").setViewName("sse");
        registry.addViewController("/async").setViewName("async");
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

    /**
     * 配置资源的处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceLocations 指的是文件放置的目录，addResourceHandler 指的是对外暴露的访问路径。
        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/images/");
    }

    /**
     * 添加 Interceptor 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截器匹配的模式
        registry.addInterceptor(new BaseUrlInterceptor()).addPathPatterns("/**");
        // 设置拦截器匹配的模式及排除的模式（可以指定多个）
        registry.addInterceptor(new TimeCostInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/js/**", "/css/**", "/image/**");
    }

    /**
     * 处理 multipart 形式的数据
     * <p>
     *     multipart 格式的数据会将一个表单拆分为多个 part，每个 part 对应一个输入域。
     *     在一般的表单输入域中，它所对应的部分中会放置文本型数据，
     *     但是如果上传文件的话，它所对应的部分可以是二进制。
     *     尽管 multipart 请求看起来很复杂，但在 SpringMVC 中处理它们很容易。
     *     首先需要配置一个 multipart 解析器，通过它来告诉 DispatcherServlet 该如何读取 multipart 请求。
     * <p>
     * 1，配置 multipart 解析器
     * <p>
     *     DispatcherServlet 将解析 multipart 请求数据的任务委托给了
     *     Spring 中 MultipartResolver 策略接口的实现类，通过它来解析 multipart 请求中的内容。
     *     从 Spring3.1 开始，Spring 内置了两个 MultipartResolver 实现类：
     *
     *     <li>CommonMultipartResolver 使用 Jakarta Commons FileUpload 解析 multipart 请求。
     *     <li>StandardServletMultipartResolver 依赖于 Servlet 3.0 对 multipart 请求的支持。
     *
     *     一般而言，StandardServletMultipartResolver 是更优的方案。
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // 配置 Jakarta Commons FileUpload multipart 解析器
    @Bean
    public MultipartResolver multipartResolver2() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource(WebConstant.TEMP_DIR));
        multipartResolver.setMaxUploadSize(2 * 1024 * 1024);
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }
}
