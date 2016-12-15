package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rest.bean.Book;
import rest.dao.BookRepository;

/**
 * 实现 RESTful 功能的 Spring MVC 控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/14.
 */
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * 这里返回的不再是视图的逻辑名，而是 Java 对象。
	 * 在 REST API 的情况下，HTML 并不是合适的数据表述形式。
	 * <p></p>
	 * 控制器本身并不关心资源如何表述，它以 Java 对象的方式来处理资源。
	 * 控制器完成了它的工作之后，资源才会被转化成最适合客户端的形式。
	 * 详见 关于表述.md
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Book book(@RequestParam("id") String id) {
		return bookRepository.findOne(id);
	}
}
