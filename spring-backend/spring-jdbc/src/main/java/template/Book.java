package template;

import java.io.Serializable;

/**
 * POJO
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
public class Book implements Serializable {

    private String id;
    private String title;
    private double price;

    public Book() {
    }

    public Book(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
