package wiring.adv.qualifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 自动装配的歧义性示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Animal1Config.class)
public class AnimalConfig1Test {

    @Autowired
    private Animal animal;

    @Test
    public void test(){
        // No qualifying bean of type [wiring.adv.qualifier.Animal] is defined:
        // expected single matching bean but found 2: cat,dog
        System.out.println(animal);
    }
}

@Configuration
class Animal1Config {

    // 这里有两个 Animal 类型的 bean
    @Bean
    public Animal cat() {
        return new Cat();
    }

    @Bean
    public Animal dog() {
        return new Dog();
    }
}