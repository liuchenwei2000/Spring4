package jms.mdp;

import common.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
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
                new ClassPathXmlApplicationContext("jms/mdp/mdp_config.xml");

        JmsTemplate producer = context.getBean(JmsTemplate.class);

        // 发送消息
        producer.send("hello.queue", new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(new Book("1234", "Spring in Action"));// 创建消息
            }
        });

        System.out.println("Messages have been sended.");
        TimeUnit.SECONDS.sleep(5);

        context.close();
    }
}
