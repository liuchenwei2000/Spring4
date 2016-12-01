package wiring.adv.qualifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * 解决自动装配歧义性示例
 * <p>
 *     标示首选的 bean
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Animal2Config.class)
public class AnimalConfig2Test {

    @Autowired
    private Animal animal;

    @Test
    public void test(){
        assertTrue(animal instanceof Cat);
        System.out.println(animal);
    }
}

@Configuration
class Animal2Config {

    // 通过将其中一个可选 bean 设置为首选（primary）bean 能够避免自动装配时的歧义性
    // 当遇到歧义性的时候，Spring 将会优先使用首先 bean，而不是其他可选的 beam。
    // @Primary 注解能够与 @Component 组合用在组件扫描的 bean 上，
    // 也可以与 @Bean 组合用在 Java 配置的 bean 声明中，还可以在 XML 配置中作为 bean 属性来使用。
    @Bean
    @Primary
    public Animal cat() {
        return new Cat();
    }

    @Bean
    public Animal dog() {
        return new Dog();
    }
}

