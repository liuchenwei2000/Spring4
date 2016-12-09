package myapp.controller.exception;

/**
 * 自定义异常类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
public class PetAlreadyRemovedException extends RuntimeException {

    public PetAlreadyRemovedException(String message) {
        super(message);
    }
}
