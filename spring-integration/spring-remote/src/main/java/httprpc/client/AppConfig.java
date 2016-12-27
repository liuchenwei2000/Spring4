package httprpc.client;

import common.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Hessian 客户端配置类
 * <p>
 *     在客户端代码中，基于 RMI 的服务于基于 Hessian 的服务之间唯一的差别在于
 *     要使用 HessianProxyFactoryBean 来代替 RmiProxyFactoryBean。
 *     Burlap 和 HttpInvoker 与 Hessian 的配置方式类似。
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@Configuration
@ComponentScan
public class AppConfig {

    /**
     * 装配 Hessian 服务
     */
    @Bean
    public HessianProxyFactoryBean bookService(){
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        // 设置服务的 URL
        proxy.setServiceUrl("http://localhost:8080/spring-remote/book.service");
        // 设置服务提供的接口类
        proxy.setServiceInterface(BookService.class);
        return proxy;
    }

    /**
     * 装配 HttpInvoker 服务
     */
    @Bean
    public HttpInvokerProxyFactoryBean bookService2(){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        // 设置服务的 URL
        proxy.setServiceUrl("http://localhost:8080/spring-remote/book2.service");
        // 设置服务提供的接口类
        proxy.setServiceInterface(BookService.class);
        return proxy;
    }
}
