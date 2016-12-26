package rmi.client;

import common.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 模拟 RMI 客户端
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class BookServiceClient {

    // 将服务代理装配到客户端，可以把它当成本地 bean 一样调用它的方法。
    // 客户端代码甚至不需要知道所处理的是一个 RMI 服务。
    @Autowired
    private BookService bookService;

    @Test
    public void test(){
        assertNotNull(bookService);
        System.out.println(bookService);
        System.out.println(bookService.findById("A0001"));
    }
}
