package adv.schedule;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Context Closed 监听器负责关闭线程池
 * <p>
 *     使用完毕需要手工关停线程池，在 WEB 项目中可以使用 ContextListener 来获取线程池对象并关停
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
@Component
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {

    @Resource(name = "scheduledExecutor")
    private ThreadPoolTaskScheduler scheduledExecutor;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        scheduledExecutor.shutdown();
    }
}
