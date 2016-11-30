package quickstart.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import quickstart.Knight;

/**
 * 主程序入口类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/11/30.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("quickstart/aop/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
