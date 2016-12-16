package rest.controller.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rest.bean.Book;
import rest.bean.Error;
import rest.dao.BookRepository;

/**
 * 发送错误信息到客户端示例
 * <p>
 *     1，使用 ResponseEntity
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
@Controller
@RequestMapping("/more")
public class BookMoreController {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * 使用 ResponseEntity
	 * <P>
	 *     作为 @ResponseBody 的替代方案，控制器方法可以返回一个 ResponseEntity 对象。
	 *     ResponseEntity 中可以包含响应相关的元数据（如头信息和状态码）以及要转换成资源表述的对象。
	 * <P>
	 *     除了包含响应头信息、状态码以及负载之外，ResponseEntity 还包含了 @ResponseBody 的语义，
	 *     因此负载部分将会渲染到响应体中，就像在方法上使用了 @ResponseBody 注解一样。
	 *     如果返回 ResponseEntity 的话，那就没有必要在方法上使用 @ResponseBody 注解了。
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> book(@PathVariable String id) {
		Book book = bookRepository.findOne(id);
		// 资源无法找到的时候，返回 HTTP 404 错误及详细信息
		if (book == null) {
			Error error = new Error(404, "Book [" + id + "] not found");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(book, HttpStatus.OK);
	}
}
