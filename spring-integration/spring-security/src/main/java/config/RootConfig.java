package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * RootConfig 配置
 * <p>
 *     因为 Web 相关的配置已经通过 WebConfig 配置好了，所以 RootConfig 相对很简单。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@ComponentScan(basePackages = {"myapp"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {
}
