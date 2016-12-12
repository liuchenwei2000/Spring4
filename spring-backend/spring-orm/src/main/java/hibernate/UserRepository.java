package hibernate;

/**
 * User Repository 接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
public interface UserRepository {

    User find(String id);

    String save(User user);
}
