package wiring.adv.scope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * bean 的作用域示例
 * <p>
 *     如果要为 bean 指定作用域，可以使用 @Scope 注解，
 *     它可以与 @Component 或 @Bean 一起使用。
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ScopeConfig.class)
public class ScopeConfigTest {

    @Autowired
    private Notepad notepad1;

    @Autowired
    private Notepad notepad2;

    @Autowired
    private Key key1;

    @Autowired
    private Key key2;

    @Test
    public void test(){
        assertNotNull(notepad1);
        assertTrue(notepad1 == notepad2);

        System.out.println(notepad1);
        System.out.println(notepad2);

        assertNotNull(key1);
        assertTrue(key1 != key2);

        System.out.println(key1);
        System.out.println(key2);
    }
}

@Configuration
@ComponentScan
class ScopeConfig {

    @Bean
    // 设定 bean 为 singleton 的作用域
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Notepad notepad() {
        return new Notepad();
    }
}

class Notepad {
}

@Component
// 设定 bean 为 prototype 的作用域
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Key {
}