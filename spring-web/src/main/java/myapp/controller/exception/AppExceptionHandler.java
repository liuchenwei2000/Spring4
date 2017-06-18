package myapp.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器通知示例
 * <p>
 *     控制器通知（Controller Advice）是任意带有 @ControllerAdvice 注解的类，
 *     这个类会包含一个或多个如下类型的方法：
 *
 *     <li>@ExceptionHandler 注解标注的方法
 *     <li>@InitBinder 注解标注的方法
 *     <li>@ModelAttribute 注解标注的方法
 *<p>
 *     在带有 @ControllerAdvice 注解的类中，上述的这些方法会被运用到整个应用程序
 *     所有的控制器中带有 @RequestMapping 注解的方法上。
 *     @ControllerAdvice 注解本身已经使用了 @Component，所以它会被自动扫描成组件。
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
@ControllerAdvice
public class AppExceptionHandler {

    /**
     * 控制器通知最实用的一个场景就是将所有的 @ExceptionHandler 方法
     * 收集到一个类中，这样所有控制器的额异常就能在一个地方进行一致的处理。
     */
    @ExceptionHandler(DuplicationException.class)
    public String handleDuplicationException() {
        return "error";
    }
}
