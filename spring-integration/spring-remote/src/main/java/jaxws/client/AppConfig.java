package jaxws.client;

import common.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

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
    public JaxWsPortProxyFactoryBean bookJaxWsService() throws MalformedURLException {
        JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
        proxy.setWsdlDocumentUrl(new URL("http://localhost:8888/services/BookService?wsdl"));
        proxy.setServiceName("BookService");
        proxy.setPortName("BookServiceEndpointPort");
        proxy.setServiceInterface(BookService.class);
        proxy.setNamespaceUri("http://server.jaxws/");
        return proxy;
    }
}
