package quickstart;

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
        // Spring 通过应用上下文（Application Context）装载 bean 的定义并把它们组装起来。
        // Spring 应用上下文全权负责对象的创建和组装，下面是加载 XML 文件配置的示例：
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("quickstart/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();

        // 下面是加载 Java 配置的示例：
        AnnotationConfigApplicationContext context2 =
                new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight2 = context2.getBean(Knight.class);
        knight2.embarkOnQuest();
        context2.close();
    }
}
