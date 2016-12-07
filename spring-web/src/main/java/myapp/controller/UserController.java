package myapp.controller;

import myapp.bean.User;
import myapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 3，User Controller
 * <p>
 *     Spring MVC 允许以多种方式将客户端中的数据传送到控制器的处理器方法中：
 *     <li>查询参数（Query Parameter）
 *     <li>路径变量（Path Variable）
 *     <li>表单参数（Form Parameter）
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询参数（Query Parameter）示例方法
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    // @RequestParam 用来将请求中参数值（total）绑定到本方法的参数（count）上，
    // 并且使用 defaultValue 属性设定了默认值，当请求中没有 total 参数时将会使用默认值。
    // 尽管 defaultValue 属性给定的是 String 类型，但当绑定到方法的 count 参数时会自动转换为 int。
    // 同一个方法可以有多个 @RequestParam 参数以支持多参数请求的值传递。
    public String users(Model model,
                        @RequestParam(value = "total", defaultValue = "5") int count) {
        List<User> users = userRepository.getUsers(count);
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * 路径变量（Path Variable）示例方法
     */
    // 允许在 @RequestMapping 路径中添加占位符，占位符要用大括号括起来，如下：
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    // 路径中的其他部分要与所处理的请求完全匹配，但是占位符部分可以是任意的值。
    // 方法的 id 参数前加上了 @PathVariable("id") 注解，表明在请求路径中，
    // 不管占位符部分的值是什么都会传递到处理器方法的 userid 参数中。
    public String user(Model model,
                        @PathVariable("id") String userid) {
        User user = userRepository.find(userid);
        model.addAttribute("user", user);
        return "user";
    }
}
