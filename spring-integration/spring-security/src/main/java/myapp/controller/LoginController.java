package myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录页控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Controller
@RequestMapping
public class LoginController {

    /**
     * 返回登录页逻辑视图名
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
