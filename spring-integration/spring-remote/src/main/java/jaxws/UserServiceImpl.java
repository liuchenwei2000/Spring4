package jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
@WebService
public class UserServiceImpl implements UserService {

    @WebMethod
    public User getUserById(String id){
        User user = new User();
        user.setId(id);
        user.setCode("A0001");
        user.setName("Bill Gates");
        return user;
    }
}
