package common;

import java.io.Serializable;

/**
 * POJO
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/19.
 */
public class Book implements Serializable {

    private String id;
    private String name;

    public Book() {
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
