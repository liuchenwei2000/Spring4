package jaxws.server;

import common.Book;
import common.BookService;
import jaxws.BookServiceWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * JAX-WS 服务实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
@Component
@WebService(serviceName = "BookServiceWs",
        targetNamespace = "http://bookstore.com",
        portName = "BookServiceWsEndpointPort")
// 设置 serviceName、targetNamespace、portName 等信息，客户端调用时需要明确指定
public class BookServiceEndpoint implements BookServiceWS {

    // 仍然可以使用 Spring 管理的 bean
    @Autowired
    private BookService bookService;

    @WebMethod
    public Book getBookById(String id) {
        return bookService.findById(id);
    }
}
