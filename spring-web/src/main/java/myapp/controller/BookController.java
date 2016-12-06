package myapp.controller;

import myapp.bean.Book;
import myapp.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Book Controller
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    // 注入 BookRepository
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    // 本方法中给定了一个 Model 作为参数，这样可以将数据填充到模型中。
    // Model 实际上就是一个 Map，其实直接使用 Map 作为参数也是可以的。
    // Model（或 Map）会被传递给视图，这样数据就能渲染到客户端了。
    public String books(Model model) {
        List<Book> books = bookRepository.getBooks(5);
        // 将 books 添加到模型中
        model.addAttribute("books", books);
        return "books";
    }
}
