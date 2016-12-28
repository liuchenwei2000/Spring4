package jaxws.server;

import common.Book;
import common.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
@Component
@WebService(serviceName = "BookService")
public class BookServiceEndpoint {

    @Autowired
    private BookService bookService;

    @WebMethod
    public Book findById(String id){
        return bookService.findById(id);
    }
}
