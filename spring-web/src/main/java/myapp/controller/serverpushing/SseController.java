package myapp.controller.serverpushing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * SSE 控制器
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/2.
 */
@Controller
public class SseController {

    @RequestMapping(value="/push", produces="text/event-stream") // 服务器端 SSE 的支持
    public @ResponseBody String push(HttpServletResponse response){
        response.setContentType("text/event-stream;charset=UTF-8");
        Random r = new Random();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data " + r.nextInt() +".";
    }

}
