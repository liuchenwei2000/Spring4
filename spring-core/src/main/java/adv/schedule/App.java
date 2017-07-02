package adv.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 主程序入口类
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);

        TimeUnit.SECONDS.sleep(12);

        context.close();
    }
}
