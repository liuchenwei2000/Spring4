package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2020/3/17.
 */
@Service
public class MainService {

    @Autowired
    private FooService fooService;

    @Autowired
    private BarService barService;

    public void test(){
        CountDownLatch latch = new CountDownLatch(1);

        fooService.doWork(latch);
        barService.doWork(latch);
    }
}
