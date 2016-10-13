package action;

import service.OperateDb;
import domin.Book;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Date;
import java.util.List;

/**
 * Created by root on 16-9-28.
 */
public class BookAction extends ActionSupport {
    private int currentPage=1;
    private Book book;
    private List<Book> list;
    private String ISBN;
    private String title;
    private int authorId;
    private String publisher;
    private Date publishDate;
    private float price;
    private String name;
    private int age;
    private String country;


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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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




    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public String findAll(){
        OperateDb od=new OperateDb();
        list=od.findAll(currentPage);
        return "list";
    }
    public String edit(){
        OperateDb od=new OperateDb();
        book=od.getBookByISBN(ISBN);
        if(book.getName()==null||"".equals(book.getName())){
            return "error";
        }else{
            return "success";
        }
    }
    public String find(){
        OperateDb od=new OperateDb();
        list=od.findBooksByName(name);
        if(list==null){
            return "error";
        }else {
            return "success";
        }
    }
}
