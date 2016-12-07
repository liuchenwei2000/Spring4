package myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 1，定义类级别的请求处理
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Controller
// @RequestMapping 注解声明在类级别上时，它会应用到控制器的所有处理器方法上。
// 处理器方法上的 @RequestMapping 注解会对类级别上的 @RequestMapping 声明进行补充。
@RequestMapping({"/index","/home"})// 可以接受 String 数组，以便支持对多个请求地址的映射
public class HomeController2 {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
