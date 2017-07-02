package adv.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务执行类
 * <p>
 *     通过 @Async 注解表明该方法是个异步方法，如果注解在类级别，
 *     则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用
 *     ThreadPoolTaskExecutor 作为 TaskExecutor。
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
@Service
public class AsyncTaskService {

    @Async("asyncExecutor")
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务: " + i);
    }

    @Async("asyncExecutor")
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1: " + (i + 1));
    }
}
