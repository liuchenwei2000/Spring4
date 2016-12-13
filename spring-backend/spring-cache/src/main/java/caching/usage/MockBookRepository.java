package caching.usage;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Book Repository Mock 实现
 * <p>
 *     可以将 Cache 的相关注解添加到接口的方法声明上，这样一来，
 *     注解会被接口的所有实现类继承，这些实现类都会应用相同的缓存规则。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Repository
public class MockBookRepository implements BookRepository{

    /**
     * @Cacheable 和 @CachePut 都可以用来填充缓存，但工作方式是不同的
     * <p>
     *     @Cacheable 首先在缓存中查找条目，如果找到了匹配的条目，那就不会再调用方法了。
     *     如果没有找到匹配的条目，方法会被调用并且返回值要放到缓存之中。
     *     @CachePut 并不会在缓存中检查匹配的值，目标方法总是会被调用，并将返回值添加到缓存之中。
     * <p>
     *     它们有一些属性是共有的，如下：
     *     <li>value(String[])：要使用的缓存名称
     *     <li>condition(String)：SpEL表达式，值是 false 的话则不会将缓存应用到方法调用上
     *     <li>key(String)：SpEL表达式，用来计算自定义的缓存 key
     *     <li>unless(String)：SpEL表达式，值是 true 的话则返回值不会放到缓存中
     * <p>
     *     一般情况下，只需使用 value 属性指定一个或多个缓存即可。
     */

    /**
     * 缓存这个方法的结果（假设 Book 对象一旦保存便不会再被修改）
     * <p>
     *     当本方法被调用时，缓存切面会拦截调用并在缓存中查找之前 CachingConfig.CACHE_NAME
     *     标识的缓存存储的返回值。默认情况下，缓存的 key 是传递给方法的参数（本例是 id）。
     *     如果能够找到这个 key 对应的值的话，就会返回这个值，方法不会再被调用。
     *     如果没有找到值的话，就会调用这个方法，并将返回值放到缓存之中，为下一次调用本方法做好准备。
     */
    @Cacheable(CachingConfig.CACHE_NAME)
    public Book findOne(String id) {
        System.out.println("...findOne(String)...");
        Book book = new Book();
        book.setId(id);
        book.setTitle("Spring in Action");
        book.setAuthor("Craig Walls");
        return book;
    }

    /**
     * @CachePut 注解的方法始终都会被调用，而且它的返回值也会被放到缓存中。
     * 这提供了一种很便利的机制，能够让我们在向缓存请求值之前预先加载缓存。
     * <p>
     *     在本例中，Book 被保存之后，很可能马上就会被查询。
     *     所以，在本方法被调用后，立即将 Book 对象放到缓存中。
     *     这样一来，当其他人通过 findOne() 等查询方法进行查找时，缓存已经准备就绪。
     * <p>
     *     默认的缓存 key 要基于方法的参数来确定，但是可以自定义缓存 key。
     *     在本例中，需要将 key 设置为所保存 Book 的 id，但是 Book 对象在传递给本方法之前还没有 id。
     *     因此，只能通过本方法返回的 Book 对象得到 id 属性的值。
     *     Spring 提供了多个用来定义缓存规则的 SpEL 扩展：
     *     <li>#root.args 传递给缓存方法的参数，形式为数组
     *     <li>#root.target 目标对象
     *     <li>#result 方法调用的返回值
     * <p>
     *     本例使用 #result.id 来引用返回的 Book 对象的 id 值。
     */
    @CachePut(value = CachingConfig.CACHE_NAME, key = "#result.id")
    public Book save(Book book) {
        System.out.println("...save(Book)...");
        book.setId("12345");
        return book;
    }

    @CacheEvict(value = CachingConfig.CACHE_NAME, condition = "")
    public void delete(String id) {
        System.out.println("...delete(String)...");
        System.out.println("Book id=" + id + " has been deleted.");
    }
}
