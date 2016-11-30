package wiring.mix;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 使用 @Import 注解将两个独立的配置类 CDConfig、CDPlayerConfig 组合在一起。
 */
@Configuration
@Import({CDConfig.class, CDPlayerConfig.class})
public class SystemConfig1 {
}
