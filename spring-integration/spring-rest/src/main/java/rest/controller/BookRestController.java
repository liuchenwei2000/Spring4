package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest.bean.Book;
import rest.dao.BookRepository;

import java.util.List;

/**
 * 为控制器默认设置消息转换
 * <p>
 *     如果在控制器类上使用 @RestController 注解来代替 @Controller 的话，
 *     Spring 将会为该控制器的所有处理方法应用消息转换功能，而不必为每个方法都添加
 *     @ResponseBody 或 @RequestBody 注解了。
 * <p>
 * Created by liuchenwei on 2016/12/15.
 */
@RestController
@RequestMapping("/rest")
public class BookRestController {

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/books", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> books() {
		return bookRepository.findAll();
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
}
