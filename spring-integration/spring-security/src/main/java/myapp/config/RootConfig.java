package myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.regex.Pattern;

/**
 * RootConfig 配置
 * <p>
 *     因为 Web 相关的配置已经通过 WebConfig 配置好了，所以 RootConfig 相对很简单。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
@ComponentScan(basePackages = {"myapp"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
        @Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)
})
public class RootConfig {

    public static class WebPackage extends RegexPatternTypeFilter {

        public WebPackage() {
            super(Pattern.compile("myapp\\.controller"));
        }
    }

    @Bean
    public DataSource dataSource(){
        return null;
    }
}