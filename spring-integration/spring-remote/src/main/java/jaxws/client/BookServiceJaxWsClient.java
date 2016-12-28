package jaxws.client;

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
public class BookServiceJaxWsClient {

    @Autowired
    private BookService bookJaxWsService;

    @Test
    public void test() {
        assertNotNull(bookJaxWsService);
        System.out.println(bookJaxWsService);
        System.out.println(bookJaxWsService.findById("A0001"));
    }
}
