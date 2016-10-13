package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnJdbc {
    public Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://dizqpcbvutsx.rds.sae.sina.com.cn:10640/bookdb";
            String user="root";
            String password="000000zsq";
            conn= DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
