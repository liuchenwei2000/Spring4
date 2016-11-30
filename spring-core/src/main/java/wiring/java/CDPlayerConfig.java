package wiring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wiring.CD;

// @Configuration 注解表明这是一个配置类，
// 该类应该包含 Spring 应用上下文创建 bean 的细节。
@Configuration
public class CDPlayerConfig {

    // @Bean 注解会通知 Spring 该方法将会返回一个对象，该对象要注册为应用上下文中的 bean。
    // 默认情况下，bean 的 id 与带有 @Bean 注解的方法名是一样的。比如本例是 myCD。
    @Bean
    public CD myCD(){
        // 方法体中包含了最终产生 bean 实例的逻辑
        return new WhiteAlbum();
    }

    // 通过 name 属性可以为 bean 指定特定 id。
    @Bean(name = "anotherCD")
    public CD myCD2(){
        // 方法体中包含了最终产生 bean 实例的逻辑
        return new Apologize();
    }

    // 方法体中可以自由发挥，充分利用 Java 的能力
    @Bean
    public CD randomCD() {
        int choice = (int) (Math.random() * 10);
        if (choice < 5) {
            return new WhiteAlbum();
        } else {
            return new Apologize();
        }
    }

    @Bean
    public CDPlayer cdPlayer(){
        // 装配 bean 的最简单方式就是引用创建 bean 的方法。
        // 因为 myCD() 方法上添加了 @Bean 注解，Spring 将会拦截所有对它的调用，
        // 并确保直接返回该方法创建的 bean，而不是每次都对其进行实际的调用。
        return new CDPlayer(myCD());
    }

    @Bean
    public CDPlayer cdPlayer2(){
        // 这里装配的 CD bean 和上面方法中的是同一个实例。
        return new CDPlayer(myCD());
    }

    // 本方法请求传入一个 CD 类型的 id 为参数名的 bean，通过这种方式引入其他的 bean 通常是最佳选择
    // 因为它不会要求将 CD 声明到同一个配置类之中，实际上它可以通过组件扫描自动发现或者通过 XML 来进行配置。
    // 可以将配置分散到多个配置类、XML 文件以及自动扫描和装配 bean 中，只要功能完整健全即可。
    // 不管 CD 是采用什么方式创建出来的，Spring 都会将其传入到配置方法中并用来创建 CDPlayer bean。
    @Bean
    public CDPlayer cdPlayer3(CD anotherCD){
        return new CDPlayer(anotherCD);
    }
}
