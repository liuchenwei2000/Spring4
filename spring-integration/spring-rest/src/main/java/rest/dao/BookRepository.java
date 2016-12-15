package rest.dao;

import org.springframework.stereotype.Repository;
import rest.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * BookRepository
 * <p>
 *     Mock 实现
 * <p>
 * Created by liuchenwei on 2016/12/14.
 */
@Repository
public class BookRepository {

    public Book findOne(String id) {
        return new Book(id, "Spring in Action", "Craig Walls");
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("12345", "Spring in Action", "Craig Walls"));
        books.add(new Book("34523", "Thinking in Java", "Bruce Eckel"));
        books.add(new Book("38765", "Hello Android", "Unknown"));
        return books;
    }

    public Book save(Book book) {
        book.setId("88776");
        return book;
    }
}
