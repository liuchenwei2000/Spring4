package caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启用注解驱动的缓存的 Java 配置示例
 * <p>
 *     在配置类上添加 @EnableCaching 就能启用注解驱动的缓存。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Configuration
@EnableCaching
public class CachingConfig {

    /**
     * 声明缓存管理器
     * <p>
     *     缓存管理器是 Spring 缓存抽象的核心，它能够与多个流行的缓存实现进行集成。
     * <p>
     *     Spring 内置了五个缓存管理器实现，如下：
     *     <li>SimpleCacheManager</li>
     *     <li>NoOPManager</li>
     *     <li>ConcurrentMapCacheManager</li>
     *     <li>CompositeCacheManager</li>
     *     <li>EhCacheCacheManager</li>
     * <p>
     *     除了核心的 Spring 框架，Spring Data 又提供了两个缓存管理器：
     *     <li>RedisCacheManager</li>
     *     <li>GemfireCacheManager</li>
     * <p>
     *     所以，在为 Spring 的缓存抽象选择缓存管理器时，可以有多种方案，
     *     具体选择哪一个取决于想要使用的底层缓存供应商。
     */
    @Bean
    public CacheManager cacheManager() {
        // ConcurrentMapCacheManager 使用 ConcurrentHashMap 作为其缓存存储，
        // 这在开发、测试或者基础应用而言是个不错的选择，企业级生产环境就不理想了。
        return new ConcurrentMapCacheManager();
    }
}
