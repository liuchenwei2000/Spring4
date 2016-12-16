package rest.controller.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import rest.bean.Book;
import rest.dao.BookRepository;

import java.net.URI;

/**
 * 在响应中设置头部信息
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
@Controller
@RequestMapping("/more3")
public class BookMoreController3 {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * 成功创建了资源，状态可以是 OK，但不仅仅是 OK。
	 * HTTP 201 不仅能够表明请求成功完成，而且还能描述创建了新资源。
	 * 另外，当创建新资源的时候，将资源的 URL 放在响应的 Location
	 * 头部信息中并返回给客户端是一种很好的方式。
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> addBook(@RequestBody Book book, UriComponentsBuilder ucb) {
		book = bookRepository.save(book);

		// HttpHeaders 有一些便利的 setter 方法，用来设置常见的 HTTP 头部信息
		HttpHeaders headers = new HttpHeaders();

		// Spring 提供的 UriComponentsBuilder 用来构建 UriComponents 实例，
		// 借助于 UriComponents 对象能够获得适合设置给 Location 头部信息的 URL。
		// 为了使用 UriComponentsBuilder 需要将其作为处理器方法的一个参数传入。
		// 它会预先配置已知的信息如 host、端口以及 Servlet 内容（这些基础信息是从处理器方法的请求中获取的），
		// 基于这些信息，代码会通过设置路径的方式构建 UriComponents 其余的部分。
		URI location = ucb.path("/more2/book/").path(book.getId()).build().toUri();
		headers.setLocation(location);

		ResponseEntity<Book> responseEntity = new ResponseEntity<>(book, headers,
				HttpStatus.CREATED);

		return responseEntity;
	}
}
