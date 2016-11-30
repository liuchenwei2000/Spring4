package wiring.auto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于 JUnit 的 Spring 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/11/30.
 */
// 此项配置以便在测试开始的时候自动创建 Spring 的应用上下文
@RunWith(SpringJUnit4ClassRunner.class)
// 此项配置会告诉应用上下文需要在哪里加载配置
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

    @Autowired
    private CDPlayer cdPlayer;

    @Test
    public void test(){
        Assert.assertNotNull(cdPlayer);
        cdPlayer.play();
    }
}
