package wiring.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主程序入口类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/11/30.
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CDPlayerConfig.class);

        CDPlayer cdPlayer = (CDPlayer) context.getBean("cdPlayer");
        cdPlayer.play();

        cdPlayer = (CDPlayer) context.getBean("cdPlayer2");
        cdPlayer.play();

        cdPlayer = (CDPlayer) context.getBean("cdPlayer3");
        cdPlayer.play();

        context.close();
    }
}
