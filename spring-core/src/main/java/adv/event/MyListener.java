package adv.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义事件监听器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {

    /**
     * 对事件进行处理
     */
    @Override
    public void onApplicationEvent(MyEvent event) {
        String msg = event.getMessage();
        System.out.println("MyListener 接受到了 MyPublisher 发布的消息:" + msg);
    }
}
