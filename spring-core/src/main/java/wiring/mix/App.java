package wiring.mix;

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
        // 1，两个 Java 配置混合使用示例
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SystemConfig1.class);

        CDPlayer cdPlayer = (CDPlayer) context.getBean("cdPlayer");
        cdPlayer.play();

        context.close();

        // 2，在 Java 配置中引用 XML 配置示例
        context = new AnnotationConfigApplicationContext(SystemConfig2.class);

        cdPlayer = (CDPlayer) context.getBean("cdPlayer");
        cdPlayer.play();

        context.close();

        // 3，在 XML 配置中引用 Java 配置示例
        ClassPathXmlApplicationContext context2 =
                new ClassPathXmlApplicationContext("wiring/mix/system-config.xml");

        cdPlayer = (CDPlayer) context2.getBean("cdPlayer");
        cdPlayer.play();

        context2.close();
    }
}
