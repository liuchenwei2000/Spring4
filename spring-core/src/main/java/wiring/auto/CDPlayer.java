package wiring.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiring.CD;

// 显式指定 bean 的 id 为 player
@Component("player")
public class CDPlayer {

    private CD cd;

    // @Autowired 注解用来完成自动装配的工作
    // 这表明当 Spring 创建 CDPlayer bean 的时候，会传入一个 CD 类型的 bean（如果有的话）
    @Autowired
    public CDPlayer(CD cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }


    private String name;

    // @Autowired 注解既可以作用于构造器上，也可以作用于 set 方法上。
    // 不论是哪种方式，Spring都会尝试满足方法参数上所声明的依赖。
    // 如果没有匹配的 bean，那么在应用上下文创建的时候会抛出异常。
    // 也可以像下面这样将 required 属性设为 false 从而在没有匹配到 bean 的时候不会报错。
    @Autowired(required = false)
    public void setName(String name) {
        this.name = name;
    }
}
