package jaxws.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

/**
 * Spring MVC 配置类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@ComponentScan
public class JaxWsConfig {

    /**
     * SimpleJaxWsServiceExporter 作为 JAX-WS 服务导出器，要求 JAX-WS 运行时支持将
     * 端点（Endpoint）发布到指定地址上，JDK 自带的 JAX-WS 可以符合要求，但其他的参考实现未必。
     * <p>
     * SimpleJaxWsServiceExporter 把 Spring 管理的 bean 发布为 JAX-WS 运行时中的服务端点。
     * 当启动的时候，它会搜索 Spring 应用上下文来查找所有使用 @WebService 注解的 bean。
     * 当找到符合的 bean 时，它默认使用 "http://localhost:8080" 地址将 bean 发布为 JAX-WS 端点。
     */
    @Bean
    public SimpleJaxWsServiceExporter jaxWsServiceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:8888/services/");// 设置基本地址
        return exporter;
    }
}
