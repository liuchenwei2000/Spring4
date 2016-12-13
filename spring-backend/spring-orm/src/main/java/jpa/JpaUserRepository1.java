package jpa;

import common.User;
import common.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * 基于 JPA 实现的 User Repository 第一种方式
 * <p>
 *     基于纯粹的 JPA 方式（EntityManagerFactory）远胜基于模板的 JPA（JpaTemplate）。
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@Repository
@Transactional
public class JpaUserRepository1 implements UserRepository {

    // 注入 EntityManagerFactory
    @PersistenceUnit
    private EntityManagerFactory emf;

    private EntityManager currentEntityManager(){
        // 因为 EntityManager 并不是线程安全的，
        // 所以每个持久化方法使用它时都需要创建一个新的对象。
        return emf.createEntityManager();
    }

    @Override
    public User find(String id) {
        return currentEntityManager().find(User.class, id);
    }

    @Override
    public String save(User user) {
        currentEntityManager().persist(user);
        return user.getId().toString();
    }
}
