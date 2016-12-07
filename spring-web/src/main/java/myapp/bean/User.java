package myapp.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class User {

    private String id;

    // Java Validation API 定义了多个注解，可以放在属性上，从而限制这些属性的值。
    // 只要保证 classpath 下包含这个 Java API 的实现即可，本例使用 Hibernate Validator。
    @NotNull // 非空
    @Size(min = 2, max = 16) // 2 到 16 个字符
    private String name;

    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
