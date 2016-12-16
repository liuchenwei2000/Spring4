package client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rest.bean.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 RestTemplate 进行 GET 请求
 * <p>
 *     除了返回类型，getForEntity() 方法就是 getForObject() 方法的镜像。
 *     它们都执行根据 URL 检索资源的 GET 请求，都能将资源根据 responseType 参数匹配为一定的类型。
 *     唯一的区别在于 getForObject() 只返回所请求类型的对象，
 *     而 getForEntity() 会返回锁请求的对象以及响应相关的额外信息。
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class RestClient4Get {

    public static void main(String[] args) {
        String url = "http://localhost:8080/spring-rest/api/book/{id}";
        String bookId = "21234";

        /** getForObject() 示例 */

        // RestTemplate 可以接受参数化 URL
        // URL 中的 {id} 占位符最终将会用方法的 id 参数来填充。
        RestTemplate restTemplate = new RestTemplate();

        // getForObject 方法的最后一个参数是可变参数列表，每个参数都会按出现顺序插入到指定 URL 的占位符中。
        Book book = restTemplate.getForObject(url, Book.class, bookId);
        System.out.println(book);

        // 另一种方案是将 id 参数放到 Map 中，并以 id 作为 key，
        // 然后将这个 Map 作为最后一个参数传递给 getForObject 方法。
        Map<String,String> params = new HashMap<>();
        params.put("id", bookId);

        book = restTemplate.getForObject(url, Book.class, params);
        System.out.println(book);

        // 这里没有任何形式的 JSON 解析和对象映射，getForObject 内部将响应体转换为对象，
        // 它实现这些需要依赖 HTTP 消息转换器。

        /** getForEntity() 示例 */

        // getForObject() 只返回资源（通过 HTTP 信息转换器将其转换为 Java 对象），
        // getForEntity() 中返回相同的对象，而且 ResponseEntity
        // 还带有关于响应的额外信息，如 HTTP 状态码和响应头。

        ResponseEntity<Book> entity = restTemplate.getForEntity(url, Book.class, bookId);

        book = entity.getBody();// 资源对象
        System.out.println(book);

        // 返回一个 HttpHeaders 对象，该对象提供了多个便利的方法来查询响应头。
        HttpHeaders headers = entity.getHeaders();
        System.out.println("Accept=" + headers.getAccept());
        System.out.println("Allow=" + headers.getAllow());
        System.out.println("ContentType=" + headers.getContentType());
        System.out.println("LastModified=" + headers.getLastModified());
        System.out.println("Location=" + headers.getLocation());
        System.out.println("StatusCode=" + entity.getStatusCodeValue());
    }
}
