package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 初始化 Spring Boot 配置的简单启动类
 * <p>
 *     需要有个特殊的类来启动 Spring Boot 应用，Spring 本身并不知道自动配置的任何信息。
 *     直接运行本类的 main 方法即可进行 Web 访问，也可以将项目打包成 war 部署到 Tomcat 中运行。
 * <p>
 * Created by liuchenwei on 2016/12/22.
 */
@ComponentScan
@EnableAutoConfiguration // 启用 Spring Boot 的自动配置特性
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 告诉 Spring Boot 根据 Application 中的配置及命令行中的参数来运行
        SpringApplication.run(Application.class, args);
    }
}
