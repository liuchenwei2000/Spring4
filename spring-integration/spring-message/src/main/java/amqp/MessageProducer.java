package amqp;

import common.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * <p>
 *     Spring AMQP 提供了 RabbitTemplate 来消除 RabbitMQ 发送和接收消息相关的样板代码。
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/21.
 */
@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送 Book 类型的消息
     */
    public void sendBookMessage(Book book) {
        // 三个参数分别是：Exchange 名称、routing key 以及要发送的对象
        // 这里并没有指定消息该路由到何处、要发送给哪个队列以及期望哪个消费者来获取消息。
        rabbitTemplate.convertAndSend("book.exchange","book.queue",book);
        // 如果 rabbitTemplate 配置了默认的 Exchange 和 routing key，可以直接省略掉这两个参数
//        rabbitTemplate.convertAndSend(book);
    }

    /**
     * 发送 String 类型的消息
     */
    public void sendStringMessage(String str) {
        // 可以使用较低等级的 send() 方法来发送 Message 对象，如下：
        Message message = new Message(str.getBytes(), new MessageProperties());
        rabbitTemplate.send("book.exchange", "book.queue", message);
        // 类似的，send() 方法也有直接使用 rabbitTemplate 默认值的重载形式。
//        rabbitTemplate.send(message);

        // 使用 send() 方法的技巧在于构造要发送的 Message 对象，如果消息的负载时复杂对象的话，那就复杂多了。
        // 鉴于此，RabbitTemplate 还提供了 convertAndSend() 方法，它会自动将对象转换为 Message。
        // 它需要一个消息转换器来完成转换工作，默认的消息转换器是 SimlpeMessageConverter，
        // 适用于 String、Serializable 实例以及字节数组。
        // 另外 Spring AMQP 还提供了其他几种有用的消息转换器，其中包括使用 JSON 和 XML 数据的消息转换器。
    }
}
