package wiring.auto;

import org.springframework.stereotype.Component;
import wiring.CD;

// 这个注解表明该类会作为组件类，并告知 Spring 要为这个类创建 bean
@Component
// 如果没有显式配置 id，Spring 会根据类名为其指定一个 id，
// 本类的 bean id 为 myCD，即将类名的第一个字母变为小写。
public class MyCD implements CD {

    public static final String TITLE = "Apologize";
    public static final String ARTIST = "OneRepublic";

    @Override
    public void play() {
        System.out.println("Playing " + TITLE + " by " + ARTIST);
    }
}
