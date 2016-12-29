package jms.rpc.client;

import jms.rpc.AlertService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class RpcClient {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("jms/rpc/client/rpc_client_config.xml");

        AlertService alertService = context.getBean(AlertService.class);
        alertService.sendEmail("lcw@qq.com");

        System.out.println("[Client]I send an alert.");

        TimeUnit.SECONDS.sleep(1);

        context.close();
    }
}
