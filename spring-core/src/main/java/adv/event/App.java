package adv.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主程序入口类
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/1.
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyEventConfig.class);

        MyPublisher myPublisher = context.getBean(MyPublisher.class);

        myPublisher.publish("Hello World");

        context.close();
    }
}
