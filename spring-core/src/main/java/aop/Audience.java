package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 观众类
 * <p>
 *     本类不仅仅是一个 POJO，还是一个切面。
 * <p>
 * Created by liuchenwei on 2016/12/5.
 */
// 使用 @Aspect 注解定义切面
@Aspect
public class Audience {

    /**
     * 表演之前 关闭手机
     */
    @Before("execution(* aop.Performance.perform(..))")
    public void silenceCellPhones(){
        System.out.println("Silencing cell phones");
    }

    /**
     * 表演之前 落座
     */
    @Before("execution(* aop.Performance.perform(..))")
    public void takeSeats(){
        System.out.println("Taking seats");
    }

    /**
     * @Pointcut 注解能够在一个切面内定义可重用的切点、
     * 该方法的实际内容并不重要，在这里它实际上应该是空的。
     * 其实本方法只是一个标识，供 @Pointcut 注解依附。
     */
    @Pointcut("execution(* aop.Performance.perform(..))")
    public void performance(){}

    /**
     * 表演之后 鼓掌
     */
    // 直接使用切点
    @AfterReturning("performance()")
    public void applause(){
        System.out.println("CLAP CLAP CLAP!!!");
    }

    /**
     * 表演失败 要求退票
     */
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("Demanding a refund");
    }

    /**
     * 环绕表演 提醒要开始/结束了
     *
     * @param joinPoint 代表被通知的方法对象
     */
    @Around("performance()")
    public void attention(ProceedingJoinPoint joinPoint){
        System.out.println("starting......");
        try {
            joinPoint.proceed();// 执行被通知的方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("finished......");
    }
}
