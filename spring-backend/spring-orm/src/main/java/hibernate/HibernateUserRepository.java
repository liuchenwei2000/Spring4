package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 基于 Hibernate 实现的 Book Repository
 * <p>
 *     最佳实现不再是使用 HibernateTemplate，而是使用上下文 Session。
 *     通过这种方式，会直接将 Hibernate SessionFactory 注入到
 *     Repository 中，并使用它来获取 Session。
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Repository
public class HibernateUserRepository implements UserRepository {

    // 注入 SessionFactory
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        // 获取当前事务的 Session
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User find(String id) {
        // 使用当前 session
        return (User) currentSession().get(User.class, id);
    }

    @Override
    public String save(User user) {
        Serializable id = currentSession().save(user);
        return id.toString();
    }
}
