package service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import domin.Author;

public class AuthorService {
    public Integer validateAuthor(Author author) {
        Connection conn = getConn();
        String sql = "select * from author";//对数据库中的authors表进行扫描
        PreparedStatement pstmt;
        System.out.println("0000");
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){//rs是按照authors表中，从上往下一个一个数下来，如果跟author一样，则返回1表示找到了，这个author是从前端传到后端的，待会儿会说在哪儿
                if(rs.getString(1).equals(author.getAuthorID())&&rs.getString(2).equals(author.getName()))
                    return 1;
            }
            pstmt.close();//记得用完数据库要把数据库关掉
            conn.close();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private static Connection getConn() {//获得数据库，写成一个函数，省的每次调用数据库都重复写一遍，只需要调用这个函数就可以了
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookdb";
        String username = "root";
        String password = "000000zsq";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}