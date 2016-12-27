package httprpc.client;

import common.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 模拟 Hessian 客户端
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class BookServiceHttpClient {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookService bookService2;

    @Test
    public void test() {
        assertNotNull(bookService);
        System.out.println(bookService);
        System.out.println(bookService.findById("A0001"));

        assertNotNull(bookService2);
        System.out.println(bookService2);
        System.out.println(bookService2.findById("A0001"));
    }
}
