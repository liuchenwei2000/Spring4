package myapp.dao;

import myapp.bean.Book;

import java.util.List;

/**
 * Book DAO 接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
public interface BookRepository {

    List<Book> getBooks(int count);
}
