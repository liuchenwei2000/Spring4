package myapp.dao;

import myapp.bean.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Book DAO 实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    public List<Book> getBooks(int count) {
        List<Book> books = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.setId("book" + (i + 1));
            book.setTitle("title" + (i + 1));
            book.setAuthor("author" + (i + 1));
            books.add(book);
        }
        return books;
    }
}
