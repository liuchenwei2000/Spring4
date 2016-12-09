package myapp.controller.exception;

/**
 * 重复异常
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
public class DuplicationException extends RuntimeException {

    public DuplicationException(String message) {
        super(message);
    }
}
