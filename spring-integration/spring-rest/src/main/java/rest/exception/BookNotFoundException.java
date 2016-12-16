package rest.exception;

/**
 * BookNotFoundException
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class BookNotFoundException extends RuntimeException {

    private String bookId;

    public BookNotFoundException(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
