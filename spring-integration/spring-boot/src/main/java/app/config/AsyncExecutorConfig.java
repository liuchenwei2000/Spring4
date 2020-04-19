package app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 异步任务配置
 * <p>
 * <p>
 * Created by liuchenwei on 2017/6/12
 */
@Configuration
@EnableAsync
public class AsyncExecutorConfig implements AsyncConfigurer {

    private static Logger logger = LoggerFactory.getLogger(AsyncExecutorConfig.class);

    /**
     * 线程池维护线程的最少数量
     */
    private int corePoolSize = 5;

    /**
     * 线程池维护线程的最大数量
     */
    private int maxPoolSize = 10;

    /**
     * 缓存队列
     */
    private int queueCapacity = 9999;


    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    private static class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            logger.error("Exception occurs in async method: " + throwable.getMessage(), throwable);
        }
    }

}
