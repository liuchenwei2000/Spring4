package httprpc.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

/**
 * Spring MVC 配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 2，配置一个 URL 映射来确保 DispatcherServlet 把请求转给 hessianBookService
     */
    @Bean
    public HandlerMapping hessianMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties props = new Properties();
        props.setProperty("book.service", "hessianBookService");// 建立 hessian 服务映射关系
        props.setProperty("book2.service", "httpinvokerBookService");// 建立 httpinvoker 服务映射关系
        mapping.setMappings(props);
        return mapping;
    }
}
