package jpa.springdata;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 使用 Java 配置的方式配置 Spring Data JPA
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Configuration
// 使用下面的注解，将会自动扫描 jpa.springdata 寻找扩展自
// Spring Data JPA Repository 的接口，并为其创建实现类。
@EnableJpaRepositories(basePackages = "jpa.springdata")
public class JavaConfig {
}
