package jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

import static jms.JmsConst.*;

/**
 * 消息消费者
 * <p>
 *     使用传统的 JMS 发送消息（不使用 Spring），都是些类似 JDBC 的模板化的代码。
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class MessageConsumer {

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
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = new ActiveMQQueue(QUEUE_NAME);
            // 创建消息消费者
            javax.jms.MessageConsumer consumer = session.createConsumer(destination);
            // 接收消息
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("MESSAGE:[" + message.getText() + "]");
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
                System.out.println(e.getMessage());
            }
        }
    }
}
