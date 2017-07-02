package adv.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 自定义事件发布器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
@Component
public class MyPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 发布消息
     */
    public void publish(String msg) {
        // 使用 ApplicationContext 的 publishEvent 方法来发布事件
        applicationContext.publishEvent(new MyEvent(this, msg));
    }
}
