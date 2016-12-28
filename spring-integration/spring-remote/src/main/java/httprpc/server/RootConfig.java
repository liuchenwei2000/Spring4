package httprpc.server;

import common.BookService;
import common.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * RootConfig 配置
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
@Configuration
@ComponentScan(basePackages = {"httprpc.server", "jaxws.server"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {

    /**
     * 使用 HessianServiceExporter 导出 Hessian 服务
     * <p>
     * HessianServiceExporter 可以把 POJO 的 public 方法发布成 Hessian 服务的方法。
     * 它实际上是一个 Spring MVC 控制器，接收 Hessian 请求，并将这些请求转换成对导出 POJO 的方法调用。
     */
    @Bean
    public HessianServiceExporter hessianBookService(BookService service) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);// 设置实现了 BookService 的 bean
        exporter.setServiceInterface(BookService.class);// 设置服务所实现的接口
        return exporter;
    }

    /**
     * 使用 HttpInvokerServiceExporter 导出 HttpInvoker 服务
     * <p>
     * HttpInvokerServiceExporter 也是一个 SPringMVC 的控制器，通过 DispatcherServlet
     * 接收来自于客户端的请求，并将这些请求转换成对实现服务的 POJO 的方法调用。
     */
    @Bean
    public HttpInvokerServiceExporter httpinvokerBookService(BookService service) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(service);// 设置实现了 BookService 的 bean
        exporter.setServiceInterface(BookService.class);// 设置服务所实现的接口
        return exporter;
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }
}
