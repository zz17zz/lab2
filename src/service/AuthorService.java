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
        String sql = "select * from author";//�����ݿ��е�authors�����ɨ��
        PreparedStatement pstmt;
        System.out.println("0000");
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){//rs�ǰ���authors���У���������һ��һ���������������authorһ�����򷵻�1��ʾ�ҵ��ˣ����author�Ǵ�ǰ�˴�����˵ģ��������˵���Ķ�
                if(rs.getString(1).equals(author.getAuthorID())&&rs.getString(2).equals(author.getName()))
                    return 1;
            }
            pstmt.close();//�ǵ��������ݿ�Ҫ�����ݿ�ص�
            conn.close();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private static Connection getConn() {//������ݿ⣬д��һ��������ʡ��ÿ�ε������ݿⶼ�ظ�дһ�飬ֻ��Ҫ������������Ϳ�����
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookdb";
        String username = "root";
        String password = "000000zsq";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,���ض�Ӧ����
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}