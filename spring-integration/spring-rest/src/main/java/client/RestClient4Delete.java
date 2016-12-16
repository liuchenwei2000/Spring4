package client;

import org.springframework.web.client.RestTemplate;

/**
 * 使用 RestTemplate 进行 DELETE 请求
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class RestClient4Delete {

    public static void main(String[] args) {
        String url = "http://localhost:8080/spring-rest/api/book/{id}";
        String bookId = "21234";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, bookId);
    }
}
