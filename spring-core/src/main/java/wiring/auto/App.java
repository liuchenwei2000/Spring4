package wiring.auto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主程序入口类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/11/30.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("wiring/auto/cdplayer.xml");
        CDPlayer cdPlayer = context.getBean(CDPlayer.class);
        cdPlayer.play();
        context.close();

        AnnotationConfigApplicationContext context2 =
                new AnnotationConfigApplicationContext(CDPlayerConfig.class);
        cdPlayer = context2.getBean(CDPlayer.class);
        cdPlayer.play();
        context2.close();
    }
}
