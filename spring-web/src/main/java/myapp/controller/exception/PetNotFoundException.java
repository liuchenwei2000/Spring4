package myapp.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
// @ResponseStatus 注解将异常映射为特定的状态码，本例是 404
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pet Not Found")
public class PetNotFoundException extends RuntimeException {
}
