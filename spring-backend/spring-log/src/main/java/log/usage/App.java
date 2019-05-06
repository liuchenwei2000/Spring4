package log.usage;

import log.LogConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 缓存测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LogConfig.class)
public class App {

    @Autowired
    private SaveService saveService;

    @Test
    public void test() {
        saveService.save("1");
    }
}
