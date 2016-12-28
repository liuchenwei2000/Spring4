package jaxws;

import common.Book;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * JAX-WS 风格的接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/28.
 */
@WebService(serviceName = "BookServiceWs",
        targetNamespace = "http://bookstore.com",
        portName = "BookServiceWsEndpointPort")
public interface BookServiceWS {

    @WebMethod
    Book getBookById(String id);
}
