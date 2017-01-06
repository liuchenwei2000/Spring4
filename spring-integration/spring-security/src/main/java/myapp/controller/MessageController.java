package myapp.controller;

import myapp.Message;
import myapp.dao.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * 主页控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String send(Model model, String message) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Message msg = new Message();
        msg.setContent(message);
        msg.setSender(user.getUsername());
        msg.setDate(new Date());

        messageRepository.sendMessage(msg);

        model.addAttribute("message", msg);
        return "message";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        Message message = messageRepository.getMessage();
        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Message> messages = messageRepository.getAllMessages();
        model.addAttribute("messages", messages);
        return "messageList";
    }
}
