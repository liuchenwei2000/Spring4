package common;

/**
 * 服务实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
public class BookServiceImpl implements BookService {

    @Override
    public Book findById(String id) {
        System.out.println("[Server] Book id is " + id);
        Book book = new Book();
        book.setId(id);
        book.setName("Spring in Action");
        book.setAuthor("Craig Walls");
        return book;
    }
}

