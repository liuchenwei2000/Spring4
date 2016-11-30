package wiring.mix;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 使用 @Import 注解和 @ImportResource 注解分别将将两个独立的配置
 * 类 CDPlayerConfig、XML 文件 config.xml 组合在一起。
 */
@Configuration
@ImportResource("classpath:wiring/mix/cd-config.xml")
@Import({CDPlayerConfig.class})
public class SystemConfig2 {
}
