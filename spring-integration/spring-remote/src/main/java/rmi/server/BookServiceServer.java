package rmi.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * 模拟 RMI 服务器
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= RmiConfig.class)
public class BookServiceServer {

    @Autowired
    private RmiServiceExporter rmiServiceExporter;

    @Test
    public void test(){
        assertNotNull(rmiServiceExporter);
        System.out.println(rmiServiceExporter);

        // 启动 RMI 服务，模拟服务器
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("warting...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
