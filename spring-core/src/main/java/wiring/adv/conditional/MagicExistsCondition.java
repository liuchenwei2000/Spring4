package wiring.adv.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现了 Condition 的类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/1.
 */
public class MagicExistsCondition implements Condition {

    /**
     * 如果本方法返回 true 就会创建带有 @Conditional 注解的 bean，否则 bean 将会被忽略。
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 借助 isAnnotated 方法能够检查带有 @Bean 注解的方法上还有什么其他的注解
        boolean annotated = metadata.isAnnotated("Override");
        // 借助 BeanDefinitionRegistry 检查 bean 的定义
        BeanDefinitionRegistry registry = context.getRegistry();
        // 借助 ConfigurableListableBeanFactory 检查 bean 是否存在，甚至探查 bean 的属性。
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 借助 ResourceLoader 检查所加载的资源
        ResourceLoader resourceLoader = context.getResourceLoader();
        // 借助 ClassLoader 检查类是否存在
        ClassLoader classLoader = context.getClassLoader();
        // 借助 Environment 检查环境变量是否存在以及它的值是什么
        Environment environment = context.getEnvironment();
        return environment.containsProperty("magic");// 检查环境是否包含 magic 属性
    }
}
