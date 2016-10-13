package domin;

import java.sql.Date;


public class Book extends Author{
    private String ISBN;
    private String title;
    private String authorId;
    private String publisher;
    private Date publishDate;
    private float price;
  


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String anthorId) {
        this.authorId = anthorId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String pulisher) {
        this.publisher = pulisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



}
