package caching.usage;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Book Repository
 * <p>
 *     可以将 Cache 的相关注解添加到接口的方法声明上，这样一来，
 *     注解会被接口的所有实现类继承，这些实现类都会应用相同的缓存规则。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
public interface BookRepository {

    @Cacheable(CachingConfig.CACHE_NAME)
    Book findOne(String id);

    Book findByAuthor(String author);

    Book findByTitle(String title);

    @CachePut(value = CachingConfig.CACHE_NAME, key = "#book.id")
    Book save(Book book);

    @CacheEvict(CachingConfig.CACHE_NAME)
    void delete(String id);
}
