package wiring.adv.value.placeholder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 2，使用属性占位符注入外部值
 * <p>
 *     Spring 一直支持将属性定义到外部的属性文件中，并使用占位符值将其插入到 bean 中。
 *     在 Spring 装配时，占位符的形式为使用 "${...}" 包装的属性名称。
 * <p>
 * Created by liuchenwei on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= UserConfig.class)
public class PlaceholderTest {

    @Autowired
    private User user;

    @Test
    public void test(){
        assertNotNull(user);
        System.out.println(user);
    }
}

@Configuration
@ComponentScan
// 指定占位符的配置文件
@PropertySource("classpath:wiring/adv/value/placeholder/app.properties")
class UserConfig {
}

