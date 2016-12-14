package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rest.Book;
import rest.BookRepository;

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
	 * 表述是 REST 中很重要的一个方面，它是关于客户端和服务器端针对某一资源是如何通信的。
	 * 任何给定的资源都几乎可以用任意的形式来进行表述。资源没有变化，只是表述它的方式变了。
	 * 如果内容要由人类用户来使用的话，那么可能需要支持 HTML 格式的资源。
	 * 根据资源的特点和应用的需求，可能还会选择使用 PDF 文档或 Excel 文档来展现资源。
	 * 对于非人类用户的使用者，比如其他的应用或调用 REST 端的代码，资源表述的首选是 JSON 和 XML。
	 * <p></p>
	 * 控制器本身并不关心资源如何表述，它以 Java 对象的方式来处理资源。
	 * 控制器完成了它的工作之后，资源才会被转化成最适合客户端的形式。
	 * Spring 提供了两种方法将资源的 Java 表述形式转换为发送给客户端的表述形式：
	 * <p>
	 * <li>内容协商（Content negotiation）：选择一个视图，它能够将模型渲染为呈现给客户端的表述形式。
	 * <li>消息转换器（Message conversion）：通过一个消息转换器将控制器所返回的对象转换为客户端的表述形式。
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Book book(@RequestParam("id") String id) {
		return bookRepository.findOne(id);
	}
}
