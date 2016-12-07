package myapp.dao;

import myapp.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User DAO 实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Component
public class UserRepositoryImpl implements UserRepository {

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
        User user = new User();
        user.setId(id);
        user.setName("Bill Gates");
        user.setAge(60);
        return user;
    }
}
