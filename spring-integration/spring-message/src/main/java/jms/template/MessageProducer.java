package jms.template;

import common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 消息生产者
 * <p>
 *     使用 Spring JmsTemplate 消除冗长和重复的 JMS 代码。
 *     JmsTemplate 可以创建连接、获得会话以及发送和接收消息。
 *     这使得可以专注于构建要发送的消息或者处理接收到的消息。
 * <p>
 *     JmsTemplate 还可以处理所有抛出的标准的 JMSException 异常，
 *     当抛出 JMSException 异常时，JmsTemplate 会捕获该异常，
 *     然后抛出非受查异常 JmsException 的子类。
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
@Component
public class MessageProducer {

    // JmsTemplate 实现了 JmsOperations 接口
    @Autowired
    private JmsOperations jmsTemplate1;

    @Autowired
    private JmsOperations jmsTemplate2;

    /**
     * 发送 Book 类型的消息
     */
    public void sendBookMessage(final Book book) {
        // 当调用 send() 方法时，JmsTemplate 将负责获得 JMS 连接、会话并代表发送者发送消息。

        // 指定目的地发送消息，使用 MessageCreator 来构造消息
        jmsTemplate1.send("hello.queue", new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(book);// 创建消息
            }
        });
    }

    /**
     * 发送 String 类型的消息
     */
    public void sendStringMessage(String message){
        // 没有指定目的地，则直接使用配置文件中配置的默认目的地。
        jmsTemplate1.convertAndSend(message);
    }

    /**
     * 发送 Book 类型的消息
     */
    public void sendBookMessage2(Book book){
        // convertAndSend() 方法会使用内置的消息转换器（Message Converter）来创建消息。
        // Book 对象在发送之前，JmsTemplate 内部会使用一个 MessageConverter 的实现类将它转换为 Message。
        // 简单地说，MessageConverter 接口负责将对象转换为 Message 同时也可以将 Message 转换为 对象。
        // 通常没有必要创建自定义的实现，Spring 已经提供了多个实现：

        // MappingJackson2MessageConverter：使用 Jackson 2 JSON 库实现消息与 JSON 格式之间的相互转换
        // MarshallingMessageConverter：使用 JAXB 库实现消息与 XML 格式之间的相互转换
        // SimpleMessageConverter：实现 String 与 TextMessage、字节数组与 ByteMessage、
        //                         Map 与 MapMessage、Serializable 对象与 ObjectMessage 之间的相互转换。

        // 默认情况下，JmsTemplate 会在 convertAndSend() 方法中会使用 SimpleMessageConverter。
        // 但是通过将消息转换器声明为 bean 并将其注入到 JmsTemplate 的 messageConverter 属性中可以控制这种行为。
        // 详见 jms/template/activemq_config1.xml
        jmsTemplate2.convertAndSend(book);
    }
}
