package client;

import org.springframework.web.client.RestTemplate;
import rest.bean.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 RestTemplate 进行 PUT 请求
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class RestClient4Put {

    public static void main(String[] args) {
        String url = "http://localhost:8080/spring-rest/api/book/{id}";
        String bookId = "21234";

        Book book = new Book(bookId,"Hello Java","Unkown");

        RestTemplate restTemplate = new RestTemplate();
        // put 方法的最后一个参数是可变参数列表，每个参数都会按出现顺序插入到指定 URL 的占位符中。
        restTemplate.put(url, book, bookId);

        // 当使用 Map 来传递模板参数时，Map 条目的每个 key 值与 URI 模板中占位符变量的名字相同。
        Map<String,String> params = new HashMap<>();
        params.put("id", bookId);

        // RestTemplate 将使用某个 HTTP 消息转换器将 Book 对象转换为一种表述形式，并在请求体中将其发送给服务器端。
        // 对象将被转换成什么样的内容类型很大程度上取决于传递给 put 方法的对象类型。
        // 如果给定一个 String 值，那么将会使用 StringHttpMessageConverter，
        // 这个值直接被写到请求体中，内容类型设置为 "text/plain"。
        // 如果给定一个 Map，那么这个 Map 的值将会被 FormHttpMessageConverter 以
        // "application/x-www-form-urlencoded" 的格式写到请求体中。
        // 如果给定的是一个 Book 对象，所以需要能够处理任意对象的信息转换器，
        // 如果类路径下包含 Jackson 库，那么它将以 "application/json" 格式将 Book 写到请求中。
        restTemplate.put(url, book, params);
    }
}
