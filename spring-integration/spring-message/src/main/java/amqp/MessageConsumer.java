package amqp;

import common.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * <p>
 *     与 JMS 类似，Spring AMQP 也提供了同步接收消息的功能。
 *     调用接收消息的方法都会立即返回，如果队列中没有等待的消息时，将会得到 null。
 *     这就需要程序自己来管理轮询（polling）以及必要的线程，实现队列的监控。
 * <p>
 * Created by liuchenwei on 2016/12/20.
 */
@Component
public class MessageConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 接收 String 类型的消息
     */
    public String receiveStringMessage() {
        // 从指定队列中获取一个消息对象
        Message message = rabbitTemplate.receive("book.queue");
        // 需要将 body 属性中的字节数组转换为想要的对象，这很繁琐
        byte[] body = message.getBody();
        return new String(body);
    }

    /**
     * 接收 Book 类型的消息
     */
    public Book receiveBookMessage() {
        // 直接使用 receiveAndConvert() 方法可以避免繁琐的转换过程，一如 convertAndSend()。
        // receiveAndConvert() 会使用与 convertAndSend() 方法相同的消息转换器，将 Message 对象转换为原始类型。
        Book book = (Book) rabbitTemplate.receiveAndConvert("book.queue");

        // 如果配置了接受消息的默认队列，甚至可以省略掉队列参数
//        rabbitTemplate.receiveAndConvert();

        return book;
    }
}
