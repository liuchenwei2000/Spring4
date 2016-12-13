package caching;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 使用 EhCache 缓存的 Java 配置示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    /**
     * 声明缓存管理器
     * <p>
     *     本方法创建了一个 EhCacheCacheManager 的实例，
     *     通过接受传入 EhCache 的 cacheManager 实例来实现。
     *     这是因为 Spring 和 EhCache 都定义了 CacheManager 类型。
     *     需要注意，EhCache 的 CacheManager 需要被注入到 Spring 的 EhCacheCacheManager 中。
     */
    @Bean
    public EhCacheCacheManager cacheManager(net.sf.ehcache.CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    /**
     * Spring 提供了 EhCacheManagerFactoryBean 来生成 EhCache 的 CacheManager。
     * 本方法会返回一个 EhCacheManagerFactoryBean 实例，因为它是一个工厂 bean，
     * 所以注册在 Spring 应用上下文中的并不是 EhCacheManagerFactoryBean 的实例，
     * 而是 CacheManager 的一个实例，因此适合注入到 EhCacheCacheManager 中。
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFB = new EhCacheManagerFactoryBean();
        // 指明 EhCache 的 XML 配置文件相对于 classpath 的位置
        ehCacheManagerFB.setConfigLocation(new ClassPathResource("caching/ehcache.xml"));
        return ehCacheManagerFB;
    }
}
