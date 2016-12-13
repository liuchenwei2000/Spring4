package caching.usage;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 启用注解驱动的缓存的 Java 配置示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = "caching.usage")
public class CachingConfig {

    public static final String CACHE_NAME = "mycache";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        // 设置 cache 名称，以便使用 cache 的方法指定使用哪个 cache
        cacheManager.setCacheNames(Arrays.asList(new String[]{CACHE_NAME}));
        return cacheManager;
    }
}
