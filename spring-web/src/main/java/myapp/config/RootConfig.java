package myapp.config;

import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.regex.Pattern;

/**
 * RootConfig 配置
 * <p>
 *     因为 Web 相关的配置已经通过 WebConfig 配置好了，所以 RootConfig 相对很简单。
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Configuration
// 自动扫描非 Web 组件，比如 @Repository 等组件，
// 扫描的过程还可以继续加载其他配置类，比如 myapp.jdbc.JdbcConfig。
@ComponentScan(basePackages = {"myapp"}, excludeFilters = {
        // excludeFilters 可以设置自动扫描时需要排除的组件（如 Web 组件）
        // 排除掉使用了 @EnableWebMvc 或 @Controller 注解的组件
        @Filter(type = FilterType.ANNOTATION, value = {EnableWebMvc.class, Controller.class}),
        // 排除掉所在包路径包含 myapp.controller 的组件
        @Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)
})
public class RootConfig {

    public static class WebPackage extends RegexPatternTypeFilter {

        public WebPackage() {
            super(Pattern.compile("myapp\\.controller"));
        }
    }

    /**
     * 可以配置一些应用后端会用到的 bean，这些 bean 可能来自于第三方 JAR。
     */
    @Bean
    public IOFileFilter fileFilter() {
        System.out.println("fileFilter()");
        return EmptyFileFilter.EMPTY;
    }
}
