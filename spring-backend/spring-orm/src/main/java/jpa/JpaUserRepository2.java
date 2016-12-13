package jpa;

import common.User;
import common.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 基于 JPA 实现的 User Repository 第二种方式
 * <p>
 *     借助 @PersistenceContext 注解解决 EntityManager 的线程不安全问题。
 *     @PersistenceContext 和 @PersistenceUnit 是 JPA 规范提供的注解，
 *     为了让 Spring 理解这些注解，需要显式配置相应的 bean：
 *     XML 文件中可以使用 <context:annotation-config> 或 <context:component-scan>
 *     Java 配置文件中需要注册 PersistenceAnnotationBeanPostProcessor。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Repository
@Transactional
public class JpaUserRepository2 implements UserRepository {

    // 注入 EntityManager
    // 实际上，@PersistenceContext 注入的只是 EntityManager 的一个代理，
    // 真正的 EntityManager 是与当前事务相关联的那一个，如果不存在这样的
    // EntityManager 的话，就会创建一个新的，因此会始终以线程安全的方式使用 EntityManager。
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User find(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public String save(User user) {
        entityManager.persist(user);
        return user.getId().toString();
    }
}
