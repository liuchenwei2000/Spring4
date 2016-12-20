package jms.template;

import common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * 消息消费者
 * <p>
 *     使用 JmsTemplate 接收消息只需要调用其 receive() 方法即可。
 *     JmsTemplate 会尝试从消息代理中获取一个消息，如果没有可用的消息，
 *     receive() 方法会一直等待，直到获得消息为止（或者超时）。
 * <p>
 * Created by liuchenwei on 2016/12/20.
 */
@Component
public class MessageConsumer {

    @Autowired
    private JmsOperations jmsTemplate1;

    @Autowired
    private JmsOperations jmsTemplate2;

    /**
     * 接收 Book 类型的消息
     */
    public Book receiveBookMessage() {
        // 接收消息
        ObjectMessage message = (ObjectMessage) jmsTemplate1.receive();
        try {
            return (Book) message.getObject();// 获得对象
        } catch (JMSException e) {
            // 抛出转换后的非受查异常
            throw JmsUtils.convertJmsAccessException(e);
        }
    }

    /**
     * 接收 String 类型的消息
     */
    public String receiveStringMessage() {
        TextMessage message = (TextMessage) jmsTemplate1.receive();
        try {
            return message.getText();
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }

    /**
     * 接收 Book 类型的消息
     */
    public Book receiveBookMessage2(){
        // 使用 receiveAndConvert() 方法直接将消息中的对象取出来
        return (Book) jmsTemplate2.receiveAndConvert();
    }
}
