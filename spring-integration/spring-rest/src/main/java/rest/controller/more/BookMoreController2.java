package rest.controller.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.bean.Book;
import rest.bean.Error;
import rest.dao.BookRepository;
import rest.exception.BookNotFoundException;

/**
 * 发送错误信息到客户端示例
 * <p>
 *     2，使用 ExceptionHandler
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
@RestController
@RequestMapping("/more2")
public class BookMoreController2 {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * 更简洁的处理器方法
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public Book book(@PathVariable String id) {
		Book book = bookRepository.findOne(id);
		// 资源无法找到的时候，抛出 BookNotFoundException
		if (book == null) {
			throw new BookNotFoundException(id);
		}
		return book;
	}

	/**
	 * 使用 ExceptionHandler
	 * <P>
	 *     错误处理器能够处理导致问题的场景，这样常规的处理器方法就只需要关心正常的逻辑处理了。
	 *     @ExceptionHandler 注解能够用到控制器方法中，用来处理特定的异常。
	 *     如果控制器的任意处理方法中抛出 BookNotFoundException，就会调用本方法来处理异常。
	 * <P>
	 */
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)// 使用该注解统一设置状态码
	public Error bookNotFound(BookNotFoundException e) {
		return new Error(404, "Book [" + e.getBookId() + "] not found");
	}
}
