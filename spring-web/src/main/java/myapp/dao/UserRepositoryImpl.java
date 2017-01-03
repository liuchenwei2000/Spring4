package myapp.dao;

import myapp.bean.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * User DAO 实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    // 模拟保存时使用的实例
    private User currentUser;

    @Override
    public List<User> getUsers(int count) {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId("user" + (i + 1));
            user.setName("name" + (i + 1));
            user.setAge(i + 1);
            users.add(user);
        }
        return users;
    }

    @Override
    public User find(String id) {
        if (currentUser != null && currentUser.getId().equals(id)) {
            return currentUser;
        }
        User user = new User();
        user.setId(id);
        user.setName("Bill Gates");
        user.setAge(60);
        return user;
    }

    @Override
    public String save(User user) {
        currentUser = user;
        user.setId("54321");
        return user.getId();
    }
}
