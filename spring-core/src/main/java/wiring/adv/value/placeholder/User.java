package wiring.adv.value.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * POJO
 */
@Component
public class User {

    private String code;
    private String name;

    // 如果依赖于组件扫描和自动装配来创建和初始化应用组件的话，可以使用 @Value 注解。
    public User(@Value("${user.code}") String code,
                @Value("${user.name}") String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
