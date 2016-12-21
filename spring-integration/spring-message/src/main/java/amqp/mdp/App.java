package amqp.mdp;

import common.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class App {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("amqp/mdp/mdp-config.xml");

        RabbitTemplate producer = context.getBean(RabbitTemplate.class);

        // 发送消息
        producer.convertAndSend(new Book("1234", "Spring in Action"));
        System.out.println("Messages have been sended.");

        TimeUnit.SECONDS.sleep(1);

        context.close();
    }
}
