package wiring.adv.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Conditional 注解示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
@Configuration
public class MagicBeanConfig {

    // @Conditional 注解可以用在带有 @Bean 注解的方法上。
    // 如果给定的条件计算结果为 true 则创建这个 bean，否则这个 bean 会被忽略。
    @Bean
    @Conditional(MagicExistsCondition.class)
    // 设置给 @Conditional 的类可以是任意实现了 Condition 接口的类型。
    public MagicBean magicBean(){
        return new MagicBean();
    }
}
