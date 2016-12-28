package jaxws.client;

import jaxws.BookServiceWS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * JAX-WS 客户端配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@Configuration
@ComponentScan
public class AppConfig {

    /**
     * 装配 JAX-WS 服务
     */
    @Bean
    public JaxWsPortProxyFactoryBean bookJaxWsService() throws MalformedURLException {
        // 使用 JaxWsPortProxyFactoryBean 可以在 Spring 中装配 JAX-WS 服务
        // 它能生成一个知道如何与 SOAP WebService 交互的代理，代理实现了服务接口。
        JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
        // 设置 JAX-WS 服务定义文件的地址
        proxy.setWsdlDocumentUrl(new URL("http://localhost:8888/services/BookServiceWs?wsdl"));
        // 设置其他一些必要信息，这些信息都可以通过上面的 WSDL 文件获知
        proxy.setServiceName("BookServiceWs");
        proxy.setPortName("BookServiceWsEndpointPort");
        proxy.setNamespaceUri("http://bookstore.com");
        // 设置代理实现的接口
        proxy.setServiceInterface(BookServiceWS.class);
        return proxy;
    }
}
