package caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 使用 Redis 缓存的 Java 配置示例
 * <p>
 *     Redis 可以用来为 Spring 缓存抽象机制存储缓存条目，Spring Data Redis
 *     提供了 RedisCacheManager，这是 CacheManager 的一个实现。
 *     RedisCacheManager 会与一个 Redis 服务器协作，并通过
 *     RedisTemplate 将缓存条目存储到 Redis 中。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    /**
     * 声明 Redis 缓存管理器
     * <p>
     *     为了使用 RedisCacheManager，需要 RedisTemplate bean
     *     以及 RedisConnectionFactory 实现类的一个 bean。
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     * RedisTemplate bean
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * Redis 连接工厂 bean
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisCF = new JedisConnectionFactory();
        jedisCF.afterPropertiesSet();
        return jedisCF;
    }
}
