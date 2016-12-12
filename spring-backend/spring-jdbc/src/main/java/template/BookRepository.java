package template;

/**
 * Book Repository 接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
public interface BookRepository {

    Book find(String id);

    void save(Book book);
}
