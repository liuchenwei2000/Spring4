package jms.rpc.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class RpcServer {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("jms/rpc/server/rpc_server_config.xml");

        System.out.println("RPC Server started...");
        TimeUnit.SECONDS.sleep(100);

        context.close();
    }
}
