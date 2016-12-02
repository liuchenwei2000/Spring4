package wiring.adv.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 1，处理外部值的最简单方式就是声明属性源并通过 Environment 来检索属性值。
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= BookConfig.class)
public class EnvironmentTest {

    @Autowired
    private Book book;

    @Test
    public void test(){
        assertNotNull(book);
        System.out.println(book);
    }
}

/**
 * 使用 @PropertySource 注解和 Environment 注入外部的值
 * 直接从 Environment 中检索属性是非常方便的，尤其是在 Java 配置中装配 bean 的时候。
 */
@Configuration
// 声明属性源，该属性文件会被加载到 Spring 的 Environment 中
@PropertySource("classpath:wiring/adv/value/app.properties")
class BookConfig {

    @Autowired
    private Environment env;

    @Bean
    public Book book() {
        // 检索属性值
        String name = env.getProperty("book.name");
        String author = env.getProperty("book.author");
        moreAboutEnvironment();
        return new Book(name, author);
    }

    /**
     * Environment 对象的更多 API 示例
     */
    public void moreAboutEnvironment(){
        // 当指定属性不存在的时候，会返回设定的默认值
        String isbn = env.getProperty("book.isbn", "ISO-9001-0001");
        System.out.println("isbn=" + isbn);

        // 强制要求指定属性必须存在，否则将会抛出异常
        String author = env.getRequiredProperty("book.author");
        System.out.println("author=" + author);

        // 检查某个属性是否存在
        boolean hasPrice = env.containsProperty("book.price");
        System.out.println("hasPrice=" + hasPrice);

        // 返回激活 profile 名称的数组
        env.getActiveProfiles();
        // 返回默认 profile 名称的数组
        env.getDefaultProfiles();
    }
}
