package myapp;

import java.util.Date;

/**
 * Message
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/6.
 */
public class Message {

    private String sender;
    private String content;
    private Date date;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
