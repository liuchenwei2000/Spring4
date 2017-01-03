package myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
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
        return resolver;
    }
}
