package jms.template;

import common.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("jms/template/activemq_config1.xml");
        MessageProducer producer = context.getBean(MessageProducer.class);
        producer.sendMessage("Hello World..");
        producer.sendMessage(new Book("1234", "Spring in Action"));
        context.close();
    }
}
