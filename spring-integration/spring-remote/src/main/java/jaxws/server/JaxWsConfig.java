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

    @Bean
    public SimpleJaxWsServiceExporter jaxWsServiceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:8888/services/");
        return exporter;
    }
}
