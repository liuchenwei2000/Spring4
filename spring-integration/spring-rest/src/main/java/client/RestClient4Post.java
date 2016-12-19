package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rest.bean.Book;

import java.net.URI;

/**
 * 使用 RestTemplate 进行 POST 请求
 * <p>
 *     postForObject() 和 postForEntity() 对 POST 请求的处理方式与
 *     发送 GET 请求的 getForObject() 和 getForEntity() 方法是类似的。
 *     另外一个方法是 postForLocation()，这是 POST 请求所特有的。
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class RestClient4Post {

    public static void main(String[] args) throws JsonProcessingException {
        String url = "http://localhost:8080/spring-rest/api/book";

        Book book = new Book(null, "Hello Java","Unkown");

        /**
         * 假设正在使用 RestTemplate 来 POST 一个新的 Book 对象到 Book 应用程序的 REST API。
         * 因为这是一个全新的 Book，服务端还不知道它。因此，它还不是真正的 REST 资源，也没有 URL。
         * 另外，在服务端创建之前，客户端并不知道 Book 的 id。
         */

        /** 在 POST 请求中获取响应对象 */
        RestTemplate restTemplate = new RestTemplate();
        // 第一个参数是资源要 POST 到的 URL，第二个参数是要发送的对象，而第三个参数是预期返回的 Java 类型。
        Book returnObj = restTemplate.postForObject(url, book, Book.class);
        System.out.println(returnObj.getId());

        // 如果想得到请求带回来的一些原数据，可以使用 postForEntity()
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(url, book, Book.class);
        Book body = responseEntity.getBody();// 返回的 Book 对象
        System.out.println(body.getId());
        // 返回的各种 HTTP 头信息
        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println(headers.getLocation());

        /** 在 POST 请求后获取资源位置 */

        // 通常并不需要将资源发送回来，毕竟将其发送到服务器端是第一位的。
        // 如果真正需要的是 Location 头信息的值，那么使用 postForLocation() 方法会更简单。
        // postForLocation() 会在 POST 请求的请求体中发送一个资源到服务器端，
        // 但是响应不再是相同的资源对象，而是新创建资源的位置。
        url = "http://localhost:8080/spring-rest/more3/book";

        URI location = restTemplate.postForLocation(url, book);
        System.out.println(location.toString());
    }
}
