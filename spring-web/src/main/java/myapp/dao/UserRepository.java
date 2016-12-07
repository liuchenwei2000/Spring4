package myapp.dao;

import myapp.bean.User;

import java.util.List;

/**
 * User DAO 接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
public interface UserRepository {

    List<User> getUsers(int count);

    User find(String id);
}
