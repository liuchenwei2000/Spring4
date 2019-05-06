package log;

import java.lang.annotation.*;

/**
 * Log 注解
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/13.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	String value() default "";
}
