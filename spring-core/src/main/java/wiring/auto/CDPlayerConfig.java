package wiring.auto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan 默认会扫描与配置类相同的包，
// 查找带 @Component 注解的类并自动为其创建一个 bean。
// 本例会自动扫描 wiring.auto 包以及其下所有子包。
@Configuration
@ComponentScan

// 明确指定扫描的基础包
// @ComponentScan("wiring.auto")
// @ComponentScan(basePackages = "wiring.auto")

// 明确指定扫描多个基础包
// @ComponentScan(basePackages = {"wiring.auto", "wiring.java"})
public class CDPlayerConfig {
}
