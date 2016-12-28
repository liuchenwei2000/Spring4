package jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/27.
 */
@WebService
public interface UserService {

    @WebMethod
    User getUserById(String id);
}
