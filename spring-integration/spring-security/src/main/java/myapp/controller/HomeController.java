package myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Controller
@RequestMapping({"/", "/index", "/home"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
