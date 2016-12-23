package myapp.controller;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 1，基本的控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
// 声明为一个控制器，@Controller 注解是辅助组件扫描的，实际上是 @Component 的细化
@Controller
public class HomeController {

    // Web 组件需要使用 RootConfig 中配置的 bean
    @Autowired
    private IOFileFilter fileFilter;

    // @RequestMapping 注解声明了它要处理的请求：
    // value 属性指定了该方法索要处理的请求路径，method 属性细化了它所处理的 HTTP 方法。
    @RequestMapping(value = "/", method = RequestMethod.GET)// 处理对 / 的 GET 请求
    public String home() {
        System.out.println(fileFilter);
        // 返回的字符串会被 Spring MVC 解读为要渲染的视图名称，
        // DispatcherServlet 会要求视图解析器将这个逻辑名称解析为实际的视图。
        return "home";// 返回视图名为 home，根据 WebConfig 中的配置会被解析为 "/WEB-INF/views/home.jso"
    }
}
