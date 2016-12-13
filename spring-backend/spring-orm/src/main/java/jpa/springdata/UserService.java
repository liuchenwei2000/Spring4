package jpa.springdata;

import common.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模拟 UserService 对 Repository 的使用
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
public class UserService {

    // JpaUserRepository 会自动继承 18 个便利的方法进行通用的 JPA 操作
    @Autowired
    private JpaUserRepository userRepository;

    public String addUser(User user){
        return userRepository.save(user).getId();
    }

    public User find(String id){
        return userRepository.findOne(id);
    }
}
