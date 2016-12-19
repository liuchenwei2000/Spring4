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
    private JmsOperations jmsOperations;

    /**
     * 发送 Book 类型的消息
     */
    public void sendMessage(final Book book) {
        // 当调用 send() 方法时，JmsTemplate 将负责获得 JMS 连接、会话并代表发送者发送消息。

        // 指定目的地发送消息，使用 MessageCreator 来构造消息
        jmsOperations.send("hello.queue", new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(book);// 创建消息
            }
        });
    }

    /**
     * 发送 String 类型的消息
     */
    public void sendMessage(String message){
        // 没有指定目的地，则直接使用配置文件中配置的默认目的地。
        // convertAndSend() 方法会使用内置的消息转换器（Message Creator）来创建消息。
        //
        jmsOperations.convertAndSend(message);
    }
}
