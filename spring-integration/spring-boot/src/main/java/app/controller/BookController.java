package app.controller;

import app.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.repository.BookRepository;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/22.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String,Object> model){
        List<Book> books = bookRepository.findAll();
        model.put("books",books);
        // 自动配置的 Thymeleaf 模板解析器会在指定的目录下查找模板，
        // 也就是说逻辑视图名 home 对应的物理文件是 resources/templates/home.html
        return "home";
    }
}
