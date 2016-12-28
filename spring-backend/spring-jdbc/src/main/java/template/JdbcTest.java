package template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 模拟调用 Repository 的客户端
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= JdbcConfig.class)
public class JdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StoredProcedureRepository procedureRepository;

    @Test
    public void test() {
        String id = "A001";
        Book book = new Book(id, "Spring in Action", 20.99);

        assertNotNull(bookRepository);
        System.out.println(bookRepository.delete(id));
        bookRepository.save(book);
        System.out.println(bookRepository.find(id));

        System.out.println("...call stored procedure...");
        assertNotNull(procedureRepository);
        System.out.println(procedureRepository.readBook("A001"));
    }
}
