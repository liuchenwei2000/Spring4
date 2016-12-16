package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.MediaType;
import rest.bean.Book;

import java.io.IOException;

/**
 * 原生的 REST 客户端示例
 * <p>
 *     本例使用 Apache HTTP Client 发起请求并使用 Jackson JSON 解析响应。
 * <p>
 * Created by liuchenwei on 2016/12/16.
 */
public class BasicClient {

    public static void main(String[] args) throws IOException {
        // REST 请求的地址
        String url = "http://localhost:8080/spring-rest/api/book/" + "72661";

        // 创建 HTTP 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HTTP GET 请求
        HttpGet request = new HttpGet(url);
        request.setHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

        // 执行请求
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        // 将相应映射为对象
        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.readValue(entity.getContent(), Book.class);

        System.out.println(book);
    }
}
