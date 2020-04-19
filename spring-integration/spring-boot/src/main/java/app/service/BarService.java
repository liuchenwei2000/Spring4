package app.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2020/3/17.
 */
@Service
public class BarService {

    @Async
    public void doWork(CountDownLatch latch) {
        System.out.println("BarService.doWork begin...");
        try {
            TimeUnit.SECONDS.sleep(5);
            latch.await(10, TimeUnit.SECONDS);
            System.out.println("BarService.doWork end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
