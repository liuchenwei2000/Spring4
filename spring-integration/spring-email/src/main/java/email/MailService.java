package email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Email 发送服务
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/21.
 */
@Component
public class MailService {

    @Autowired
    private MailSender mailSender;

    private String from = "liuchenwei2000@sina.cn";

    /**
     * 发送普通的文本邮件
     *
     * @param to      目标地址
     * @param message 正文信息
     */
    public void sendMail(String to, String message) {
        // 使用 SimpleMailMessage 对象构造消息
        SimpleMailMessage smm = new SimpleMailMessage();

        smm.setFrom(from);// 发件人
        smm.setTo(to);// 收件人
        smm.setSubject("New letter from Spring");// 主题
        smm.setText("Hello! " + message);// 设置消息文本

        mailSender.send(smm);// 发送邮件
    }
}
