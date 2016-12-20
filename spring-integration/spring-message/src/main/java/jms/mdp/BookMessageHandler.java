package jms.mdp;

import common.Book;

/**
 * Spring MDP(Message Driven POJO) 异步接收和处理消息示例
 * <p>
 *     Spring 提供了以 POJO 的方式处理消息的能力，这些消息来自于 JMS 的队列或主题。
 * <p>
 * Created by liuchenwei on 2016/12/20.
 */
public class BookMessageHandler {

    /**
     * 处理消息的方法
     */
    public void handleBookMessage(Book book) {
        System.out.println("I like this book " + book.getName());
    }
}
