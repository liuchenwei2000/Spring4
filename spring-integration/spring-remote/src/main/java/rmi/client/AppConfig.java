package rmi.client;

import common.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * RMI 客户端配置类
 * <p>
 *     传统上，RMI 客户端必须使用 RMI API 的 Naming 类从 RMI 注册表中查找服务。
 *     Spring 的 RmiProxyFactoryBean 是一个工厂 bean，它可以为 RMI 服务创建代理。
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@Configuration
@ComponentScan
public class AppConfig {

    /**
     * 装配 RMI 服务
     */
    @Bean
    public RmiProxyFactoryBean bookService(){
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        // 设置服务的 URL
        rmiProxy.setServiceUrl("rmi://localhost/BookService");
        // 设置服务提供的接口类
        rmiProxy.setServiceInterface(BookService.class);
        return rmiProxy;
    }
}
