package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    Connection con=null;

    JDBCConnection(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox","root","root@123");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox","root","root@123");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
