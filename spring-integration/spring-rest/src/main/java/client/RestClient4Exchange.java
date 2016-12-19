package client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import rest.bean.Book;

/**
 * 使用 RestTemplate 进行 交换资源 请求
 * <p>
 *     如果想在发送给服务端的请求中设置头信息的话，需要用到 exchange() 方法。
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class RestClient4Exchange {

    public static void main(String[] args) {
        String url = "http://localhost:8080/spring-rest/api/book/{id}";
        String bookId = "21234";

        RestTemplate restTemplate = new RestTemplate();
        // 下面的调用相当于 getForEntity()，exchange() 方法使用
        // HttpMethod 参数来表明要使用的 HTTP 动作。
        ResponseEntity<Book> responseEntity = restTemplate.
                exchange(url, HttpMethod.GET, null, Book.class, bookId);
        System.out.println(responseEntity.getBody());

        // exchange() 方法允许在请求中设置头信息
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept","application/json");

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        // 对于 GET 请求，没必要为 HttpEntity 设置在请求体中发送的对象，而 POST 或 PUT 请求就有必要了
        responseEntity = restTemplate.
                exchange(url, HttpMethod.GET, requestEntity, Book.class, bookId);
        System.out.println(responseEntity.getBody());
    }
}
