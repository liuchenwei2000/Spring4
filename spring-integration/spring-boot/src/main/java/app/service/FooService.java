package app.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2020/3/17.
 */
@Service
public class FooService {

    @Async
    public void doWork(CountDownLatch latch){
        System.out.println("FooService.doWork begin...");
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("FooService.doWork end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
