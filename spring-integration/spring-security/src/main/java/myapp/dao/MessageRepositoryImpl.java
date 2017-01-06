package myapp.dao;

import myapp.Message;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息 Repository 实现
 * <p>
 *     主要用来演示表达式驱动的注解使用方法，在定义安全限制方面，表达式带了极大的灵活性。
 * <p>
 * Created by liuchenwei on 2017/1/6.
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    /**
     * <code>@PreAuthorize</code> 注解的表达式会在方法调用之前执行，
     * 如果表达式的计算结果不为 true 的话，将会阻止方法执行。
     * <p>
     *     本例实现了"管理员角色可以发任意消息，员工角色只能发不超过 20 个字的消息"
     *     表达式中的 #message 直接引用了方法中的同名参数，这使得我们能够检查传入方法的参数，
     *     并将这些参数用于认证决策的制定。
     */
    @PreAuthorize("(hasRole('ROLE_ADMIN') or (hasRole('ROLE_EMPLOYEE') and #message.content.length()<=20))")
    public void sendMessage(Message message) {
        System.out.println("[" + message.getDate() + "]" + message.getSender() + " says:" + message.getContent());
    }

    /**
     * <code>@PostAuthorize</code> 注解的表达式直到方法返回才会执行，
     * 然后根据表达式计算结果决定是否抛出安全性的异常。
     * 这种事后验证一般需要基于安全保护方法的返回值来进行安全性决策，并不常见。
     * <p>
     *     本例实现了"任何人只能看到自己所发出的消息"
     *     表达式中的 returnObject 变量引用了方法的返回对象，
     *     principal 是 Spring Security 内置的对象，代表了当前认证用户的主要信息。
     *     如果表达式计算结果为 false 则会抛出一个 AccessDeniedException。
     */
    @PostAuthorize("returnObject.sender  == principal.username")
    public Message getMessage() {
        Message msg = new Message();
        msg.setContent("Hello World");
        msg.setSender("admin");
        msg.setDate(new Date());
        return msg;
    }

    /** 有时候，需要保护的并不是对方法的调用，而是传入方法的数据和方法返回的数据。*/

    /**
     * <code>@PostFilter</code> 注解能够事后对方法的返回值进行过滤。
     * 注解会使用表达式计算该方法所返回集合的每个成员，将计算结果为 false 的成员移除掉。
     * <p>
     *     本例实现了"管理员角色可以查看所有消息，其他人都能查看自己发出的消息"
     *     表达式中的 filterObject 变量引用方法的返回 List 中的某一个元素（Message），
     *     在这个 Message 对象中，如果其发送者与认证用户相同或者用户具有 ROLE_ADMIN 角色，
     *     那么这个元素最终会包含在过滤后的列表中，否则它将会被过滤掉。
     */
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_EMPLOYEE'})")
    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.sender  == principal.username")
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Message msg = new Message();
            msg.setContent(i + ". Hello World");
            msg.setSender((i % 2 == 0) ? "guest" : "admin");
            msg.setDate(new Date());

            messages.add(msg);
        }
        return messages;
    }

    /**
     * <code>@PreFilter</code> 注解能够事先对方法的参数进行过滤。
     * 注解会使用表达式计算进入该方法的集合成员，将计算结果为 false 的成员移除掉。
     * <p>
     *     本例实现了"管理员角色可以删除所有消息，其他人都能删除自己发出的消息"
     *     表达式中的 targetObject 变量代表了要进行计算的当前列表元素。
     */
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_EMPLOYEE'})")
    @PreFilter("hasRole('ROLE_ADMIN') || targetObject.sender  == principal.username")
    public void deleteMessages(List<Message> messages) {
        // ... 仅为了演示功能，没有真正运行的场景
    }
}
