package rmi.server;

import common.BookService;
import common.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * RMI 服务器端配置类
 * <p>
 *     Spring 提供了更简单的方式来发布 RMI 服务，不再用编写那些需要抛出
 *     RemoteException 异常的特定 RMI 类，只需简单地编写实现服务功能的
 *     POJO 就可以了，Spring 会处理剩余的其他事项。
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@Configuration
public class RmiConfig {

    /**
     * 发布 RMI 服务
     * <p>
     *     RmiServiceExporter 可以把任意 Spring 管理的 bean 发布为 RMI 服务。
     *     它把 POJO 包装到服务适配器中，并将服务适配器绑定到 RMI 注册表中，从而将 POJO 转换为 RMI 服务。
     */
    @Bean
    public RmiServiceExporter rmiServiceExporter(BookService bookService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        // 表明把 booService bean 发布为一个 RMI 服务
        rmiServiceExporter.setService(bookService);
        // 命名 RMI 服务
        rmiServiceExporter.setServiceName("BookService");
        // 指定 RMI 服务所实现的接口
        rmiServiceExporter.setServiceInterface(BookService.class);

        // 默认情况下，RmiServiceExporter 会尝试绑定到本地机器 1099 端口上的 RMI 注册表。
        // 如果在这个端口没有发现 RMI 注册表，它将会启动一个注册表。
        // 如果需要绑定到不同端口或主机上的 RMI 注册表，可以通过下面的方式来指定。
//        rmiServiceExporter.setRegistryHost("rmi.service.com");
//        rmiServiceExporter.setRegistryPort(1099);
        return rmiServiceExporter;
    }

    @Bean
    public BookService bookService(){
        return new BookServiceImpl();
    }
}
