package myapp.dao;

import myapp.Message;

import java.util.List;

/**
 * 消息 Repository
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/6.
 */
public interface MessageRepository {

    void sendMessage(Message message);

    Message getMessage();

    List<Message> getAllMessages();
}
