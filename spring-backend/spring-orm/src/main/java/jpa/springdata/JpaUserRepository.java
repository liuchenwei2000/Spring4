package jpa.springdata;

import common.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 借助 Spring Data，以接口定义的方式创建 Repository
 * <p>
 *     Spring Data JPA 能够终结样板式的 JPA 代码，无需一遍遍地编写相同的
 *     Repository 实现，它只需要编写 Repository 接口就行了，根本无需实现类。
 * <p>
 *     编写 Spring Data JPA Repository 的关键在于要从一组接口中挑选一个进行扩展。
 *     这里 JpaUserRepository 扩展了 JpaRepository<User, String>。
 *     通过这种方式，JpaUserRepository 进行了参数化，所以它就能知道这是一个用来持久化
 *     User 对象的 Repository，并且 User 的 id 类型为 String。另外，
 *     它还会继承执行持久化操作的通用方法，如保存、删除以及根据 id 查询等。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
public interface JpaUserRepository extends JpaRepository<User, String> {
}
