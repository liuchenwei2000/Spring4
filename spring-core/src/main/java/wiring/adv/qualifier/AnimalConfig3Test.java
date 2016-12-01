package wiring.adv.qualifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * 解决自动装配歧义性示例
 * <p>
 *     限定自动装配的 bean
 * <p>
 *     Spring 的限定符能够在所有可选的 bean 上进行缩小范围的操作，
 *     最终能够达到只有一个 bean 满足所规定的限制条件。
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Animal3Config.class)
public class AnimalConfig3Test {

    @Autowired
    // @Qualifier 注解是使用限定符的主要方式，它可以与 @Autowired
    // 和 @Inject 协同使用，在注入的时候指定想要注入进去的是哪个 bean。
    @Qualifier("dog")
    // 为 @Qualifier 注解所设置的参数就是想要注入的 bean 的 id。
    // 所有使用 @Component 注解声明的类都会创建为 bean，并且 id 为首字母变为小写的类名；
    // 所有使用 @Bean 注解所声明的方法都会创建为 bean，并且 id 为方法名。
    // 实际上，本例 @Qualifier 所引用的 bean 要具有 String 类型的 “dog” 作为限定符。
    // 如果没有指定其他的限定符的话，所有的 bean 都会给定一个默认的限定符，与 bean 的 id 相同。
    private Animal animal;

    @Autowired
    @Qualifier("bd")
    private Animal animal2;

    @Test
    public void test(){
        assertTrue(animal instanceof Dog);
        System.out.println(animal);

        assertTrue(animal2 instanceof Bird);
        System.out.println(animal2);
    }
}

@Configuration
class Animal3Config {

    // 这里有两个 Animal 类型的 bean
    @Bean
    public Animal cat() {
        return new Cat();
    }

    @Bean
    public Animal dog() {
        return new Dog();
    }

    @Bean
    // 可以为 bean 设置自己的限定符，而不是依赖于将 bean id 作为限定符。
    // 在 bean 声明上添加 @Qualifier() 注解即可设置限定符。
    // 它既可以与 @Bean 组合使用，也可以与 @Component 组合使用。
    // 这种方式可以使得 bean 的限定符与类名解耦，因此可以随便重构类名，而不必担心自动装配被破坏。
    // 在注入的地方，只要引用 bd 这个限定符即可。
    @Qualifier("bd")
    public Animal bird() {
        return new Bird();
    }
}

