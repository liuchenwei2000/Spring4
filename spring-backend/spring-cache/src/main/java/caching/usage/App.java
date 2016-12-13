package caching.usage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 缓存测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CachingConfig.class)
public class App {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test(){
        String id = "54321";

        System.out.println("invoke findOne " + id + " 1st");
        bookRepository.findOne(id);

        System.out.println("invoke findOne " + id + " 2nd");
        bookRepository.findOne(id);

        System.out.println("invoke findOne " + id + " 3rd");
        bookRepository.findOne(id);

        System.out.println("invoke save 1st");
        Book book = bookRepository.save(new Book("Thinking in Java", "Bruce Eckel"));

        System.out.println("invoke findOne " + book.getId() + " 1st");
        bookRepository.findOne(book.getId());
    }
}
