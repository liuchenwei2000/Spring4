package caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用多个缓存管理器的 Java 配置示例
 * <p>
 *     并不是只能有且只有一个缓存管理器，可以使用 CompositeCacheManager 来支持多个缓存管理器。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Configuration
@EnableCaching
public class CompositeCacheConfig {

    /**
     * 声明 CompositeCacheManager
     * <p>
     *     CompositeCacheManager 要通过一个或更多的缓存管理器来进行配置，
     *     它会迭代这些缓存管理器，以查找之前所缓存的值。
     */
    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm,
                                     RedisTemplate redisTemplate) {
        List<CacheManager> cms = new ArrayList<>();
        cms.add(new EhCacheCacheManager(cm));
        cms.add(new RedisCacheManager(redisTemplate));

        CompositeCacheManager cacheManager = new CompositeCacheManager();
        cacheManager.setCacheManagers(cms);
        return cacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFB = new EhCacheManagerFactoryBean();
        ehCacheManagerFB.setConfigLocation(new ClassPathResource("caching/ehcache.xml"));
        return ehCacheManagerFB;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisCF = new JedisConnectionFactory();
        jedisCF.afterPropertiesSet();
        return jedisCF;
    }
}
