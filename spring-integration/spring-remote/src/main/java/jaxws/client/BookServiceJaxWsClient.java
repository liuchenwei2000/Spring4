package jaxws.client;

import jaxws.BookServiceWS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 模拟 JAX-WS 客户端
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class BookServiceJaxWsClient {

    @Autowired
    private BookServiceWS bookServiceWs;

    @Test
    public void test() {
        assertNotNull(bookServiceWs);
        System.out.println(bookServiceWs);
        System.out.println(bookServiceWs.getBookById("A0001"));
    }
}
