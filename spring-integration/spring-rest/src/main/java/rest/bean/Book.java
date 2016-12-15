package rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/14.
 */
@XmlRootElement
public class Book {

    private String id;
    private String name;
    private String author;

    public Book() {
    }

    public Book(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
