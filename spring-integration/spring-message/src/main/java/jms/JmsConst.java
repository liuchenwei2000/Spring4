package jms;

import org.apache.activemq.ActiveMQConnection;

/**
 * JMS 常量类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public final class JmsConst {

    // JMS 默认连接用户名
    public static final String USER = ActiveMQConnection.DEFAULT_USER;
    // JMS 默认连接密码
    public static final String PWD = ActiveMQConnection.DEFAULT_PASSWORD;
    // JMS 默认连接地址
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    // 默认使用的 Queue 名称
    public static final String QUEUE_NAME = "hello.queue";
}
