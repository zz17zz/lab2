package service;

import domin.Author;
import domin.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OperateDb extends ConnJdbc{
    public static final int PAGE_SIZE=10;

    public List<Book> findAll(int page){
        ArrayList<Book> list=new ArrayList<Book>();
        Connection conn=getConnection();
        String sql="select * from Book";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            //ps.setInt(1,(page-1)*PAGE_SIZE);
            //ps.setInt(2,PAGE_SIZE);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setName(getAuthorById(rs.getString("AuthorID")).getName());
                book.setPublisher(rs.getString("Publisher"));
                book.setPublishDate(rs.getDate("PublishDate"));
                list.add(book);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Book> findBooksByName(String name){
        ArrayList<Book> list=new ArrayList<Book>();
        Connection conn=getConnection();
        String sql="select * from Book where AuthorID in (select AuthorID from Author where Name=?)";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setISBN(rs.getString("ISBN"));
                book.setTitle(rs.getString("Title"));
                book.setName(getAuthorById(rs.getString("AuthorID")).getName());
                book.setPublisher(rs.getString("Publisher"));
                book.setPublishDate(rs.getDate("PublishDate"));
                list.add(book);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public Boolean addBook(Book book){
        Boolean result=false;
        Connection conn=getConnection();
        String sql1="insert into Book(ISBN,Title,AuthorID,Publisher,PublishDate,Price) values(?,?,?,?,?,?)";
        int authorId=getAuthorId(book.getName());
        if(authorId==-1){
            String sql="insert into Author(Name,Age,Country) values(?,?,?)";
            try{
                PreparedStatement ps1=conn.prepareStatement(sql);
                ps1.setString(1,book.getName());
                ps1.setInt(2,book.getAge());
                ps1.setString(3,book.getCountry());
                ps1.executeUpdate();
                ps1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            authorId=getAuthorId(book.getName());
        }

        try{
            PreparedStatement ps2=conn.prepareStatement(sql1);
            ps2.setString(1,book.getISBN());
            ps2.setString(2,book.getTitle());
            ps2.setInt(3,authorId);
            ps2.setString(4,book.getPublisher());
            ps2.setDate(5,book.getPublishDate());
            ps2.setFloat(6,book.getPrice());
            if(ps2.executeUpdate()==1){
                result=true;
            }
            ps2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }

    public Boolean delete(String ISBN){
        Boolean result=false;
        String sql="delete from Book where ISBN=?";
        Connection conn = getConnection();
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,ISBN);
            if (ps.executeUpdate()==1){
                result=true;
            }
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Boolean update(Book book){
        Boolean result=false;
        String sql="update Book set Title=?,AuthorID=?,Publisher=?,PublishDate=?,Price=? where ISBN=?";
        Connection conn=getConnection();
        int authorId=getAuthorId(book.getName());
        if(authorId==-1){
            String sql2="insert into Author(Name,Age,Country) values(?,?,?)";
            try{
                PreparedStatement ps1=conn.prepareStatement(sql2);
                ps1.setString(1,book.getName());
                ps1.setInt(2,book.getAge());
                ps1.setString(3,book.getCountry());
                ps1.executeUpdate();
                ps1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            authorId=getAuthorId(book.getName());
        }
        try{
            PreparedStatement ps2=conn.prepareStatement(sql);
            ps2.setString(1,book.getTitle());
            ps2.setInt(2,authorId);
            ps2.setString(3,book.getPublisher());
            ps2.setDate(4,book.getPublishDate());
            ps2.setFloat(5,book.getPrice());
            ps2.setString(6,book.getISBN());
            if(ps2.executeUpdate()==1){
                result=true;
            }
            ps2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public Book getBookByISBN(String ISBN){
        Book book=new Book();
        String sql="select * from Book where ISBN=?";
        Connection conn=getConnection();
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,ISBN);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                book.setISBN(ISBN);
                book.setTitle(rs.getString("Title"));
                book.setAuthorId(rs.getString("AuthorID"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublishDate(rs.getDate("PublishDate"));
                book.setPrice(rs.getFloat("Price"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        Author author=getAuthorById(book.getAuthorId());
        book.setName(author.getName());
        book.setAge(author.getAge());
        book.setCountry(author.getCountry());
        return book;
    }

    private int getAuthorId(String authorName){
        int result=-1;
        String sql1="select AuthorID from Author where Name=?";
        Connection conn=getConnection();
        try{
            PreparedStatement ps=conn.prepareStatement(sql1);
            ps.setString(1,authorName);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                result=rs.getInt("AuthorID");
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private Author getAuthorById(String id){
        Connection conn=getConnection();
        Author author=new Author();
        String sql="select * from Author where AuthorID=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                author.setAuthorID(id);
                author.setName(rs.getString("Name"));
                author.setAge(rs.getInt("Age"));
                author.setCountry(rs.getString("Country"));
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return author;
    }
}
