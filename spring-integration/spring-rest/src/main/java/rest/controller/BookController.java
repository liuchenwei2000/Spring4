package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rest.bean.Book;
import rest.dao.BookRepository;

import java.util.List;

/**
 * 实现 RESTful 功能的 Spring MVC 控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/14.
 */
@Controller
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * 这里返回的不再是视图的逻辑名，而是 Java 对象。
	 * 在 REST API 的情况下，HTML 并不是合适的数据表述形式。
	 * <p>
	 * 控制器本身并不关心资源如何表述，它以 Java 对象的方式来处理资源。
	 * 控制器完成了它的工作之后，资源才会被转化成最适合客户端的形式。
	 * 详见 关于表述.md
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book book(@PathVariable String id) {
		return bookRepository.findOne(id);
	}

	/**
	 * 如果使用了消息转换功能的话，需要告诉 Spring 跳过正常的模型/视图流程，
	 * 并使用消息转换器，最简单的方式是为控制器方法添加 @ResponseBody 注解。
	 * <p>2
	 * @ResponseBody 注解会告知 Spring 要返回的对象作为资源发送给客户端，
	 * 并将其转换为客户端可接受的表述形式，具体来说，DispatcherServlet 将会考虑到请求中
	 * Accept 头信息，并查找能够为客户端提供所需表述形式的消息转换器。
	 * <p>
	 *     produces 属性表明这个方法只处理预期输出为 JSON 的请求。也就是说，
	 *     这个方法只会处理 Accept 头部信息包含 "application/json" 的请求。
	 *     其他任何类型的请求，即使它的 URL 匹配指定的路径并且是 GET 请求也不会被这个方法处理。
	 *     这样的请求会被其他的方法来处理（如果存在适当方法的话），或者返回客户端 HTTP 406 响应。
	 */
	// 在响应体中返回资源
	@RequestMapping(value = "/books", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Book> books() {
		return bookRepository.findAll();
	}

	/**
	 * REST API 也可以接受来自客户端的资源表述，如果要让控制器将客户端发送的
	 * JSON 或 XML 转换为它所使用的 Java 对象，那是非常不方便的。与 @ResponseBody 类似，
	 * @RequestBody 能告诉 Spring 查找一个消息转换器，将来自客户端的资源表述转换为对象。
	 * <p>
	 * 因为 Book 参数上使用了 @RequestBody，所以 Spring 将会查看请求中的
	 * Content-type 头部信息，并查找能够将请求体转换为 Book 对象的消息转换器。
	 * 如果客户端发送的 Book 数据是 JSON 表述形式，那么 Content-type 头部信息可能就会是 "application/json"。
	 * 在这种情况下，DispatcherServlet 会查找能够将 JSON 转换为 Java 对象的消息转换器。
	 * <p>
	 *     consumes 属性的工作方式类似于 produces 属性，它会关注请求的 Content-type 头部信息。
	 *     它会告诉 Spring 这个方法只会处理对 "/book" 的 POST 请求，并且要求请求的
	 *     Content-type 头部信息为 "application/json"。
	 *     如果无法满足这些条件的话，会由其他方法（如果存在适当方法的话）来处理请求。
	 */
	// 在请求体中接收资源
	@RequestMapping(value = "/book", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	/**
	 * 使用 DELETE 删除资源
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteBook(@PathVariable String id) {
		System.out.println("Book [" + id + "] has been deleted.");
		return id + " has been deleted.";
	}

	/**
	 * 使用 POST 新建资源
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@ResponseBody
	public Book addBook2(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	/**
	 * 使用 PUT 更新资源
	 * <p>
	 * GET 请求将资源的状态从服务器转移到客户端，而 PUT 将资源的状态从客户端转移到服务器上。
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	// 本例将响应状态设置为 HTTP 状态码 204，即请求被成功处理了，但响应体中不包含任何返回信息。
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBook(@PathVariable String id, @RequestBody Book book) {
		System.out.println(book);
		System.out.println("Book [" + id + "] has been updated.");
	}
}
