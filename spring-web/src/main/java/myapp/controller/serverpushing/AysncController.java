package myapp.controller.serverpushing;

import myapp.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 基于 Servlet3.0 异步方法处理的服务器端推送技术
 * <p>
 *     异步任务实现是通过控制器从另外一个线程返回一个 DeferredResult，
 *     这里的 DeferredResult 是从 pushService 中获得的。
 * <p>
 * Created by liuchenwei on 2017/7/3.
 */
@Controller
public class AysncController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/defer")
    public @ResponseBody DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate();
    }
}
