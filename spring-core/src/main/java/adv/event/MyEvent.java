package adv.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
public class MyEvent extends ApplicationEvent {

    private String message;

    public MyEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
