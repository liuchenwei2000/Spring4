package jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.Date;

import static jms.JmsConst.*;

/**
 * 消息生产者
 * <p>
 *     使用传统的 JMS 接收消息（不使用 Spring），都是些类似 JDBC 的模板化的代码。
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class MessageProducer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER, PWD, BROKER_URL);
        // 连接
        Connection connection = null;
        // 会话（接受或者发送消息的线程）
        Session session = null;

        try {
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 创建 session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建指定名称的消息队列
            Destination destination = new ActiveMQQueue(QUEUE_NAME);
            // 创建消息生产者
            javax.jms.MessageProducer producer = session.createProducer(destination);
            // 创建消息
            TextMessage message = session.createTextMessage("Hello World!" + (new Date()));
            // 发送消息
            producer.send(message);
            System.out.println("Send a message:[" + message.getText() + "]");
        } catch (JMSException e) {// 处理异常
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
