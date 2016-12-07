package myapp.controller;

import myapp.bean.User;
import myapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    /**
     * User 注册表单请求
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerForm() {
        return "registerForm";
    }

    /**
     * 表单参数（Form Parameter）示例方法
     * <p>
     * 处理 User 注册请求
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    // 方法接受一个 User 对象作为参数，这个对象的属性将会使用请求中同名的参数自动填充
    public String register(User user) {
        // 当处理表单的 POST 请求时，控制器需要接受表单数据并将表单数据保存为 User 对象。
        String id  = userRepository.save(user);

        // 同时为了防止重复提交，应该将浏览器重定向到新创建 User 的基本信息页。
        // 当 InternalResourceViewResolver 看到视图格式中的 "redirect:"前缀时，
        // 会自动将其解析为重定向的规则，而不是视图的名称。除了 "redirect:"，还有 "forward:" 前缀。
        return "redirect:/user/" + id;
    }

    /**
     * 带有表单校验功能的方法示例
     */
    @RequestMapping(value = "/user/register2", method = RequestMethod.POST)
    // @Valid 注解用来告知 Spring 需要确保这个对象满足校验限制
    // 在 User 属性上添加校验限制并不能阻止表单提交，如果有校验出现错误的话，
    // 可以通过 Errors 对象进行访问，Errors 对象将作为本方法的参数。
    // Errors 参数要紧跟在 @Valid 注解的参数后面。
    public String register2(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";// 如果校验出现错误，则重新返回注册表单
        }
        String id = userRepository.save(user);
        return "redirect:/user/" + id;
    }
}
