package amqp;

import common.Book;
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
                new ClassPathXmlApplicationContext("amqp/amqp-config.xml");

        // 发送消息
        MessageProducer producer = context.getBean(MessageProducer.class);
        producer.sendStringMessage("Hello World..");
        producer.sendBookMessage(new Book("1234", "Spring in Action"));

        System.out.println("Messages have been sended.");
        TimeUnit.SECONDS.sleep(1);

        // 接收消息
        MessageConsumer consumer = context.getBean(MessageConsumer.class);
        System.out.println(consumer.receiveStringMessage());
        System.out.println(consumer.receiveBookMessage());

        context.close();
    }
}
