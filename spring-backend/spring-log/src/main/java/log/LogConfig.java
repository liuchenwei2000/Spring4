package log;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/14.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("log")
public class LogConfig {
}
