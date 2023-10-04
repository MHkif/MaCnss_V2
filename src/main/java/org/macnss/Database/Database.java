package org.macnss.Database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{

    private static  final String Driver_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3303/Macnss";
    private static final String username = "root";
    private static final String password = "root";
    private static Connection connection;
    private static DataSource dataSource;

    private Database() {}

    public static synchronized Connection getConnection(){
        if (connection == null){
            try {
                Class.forName(Driver_PATH);
                connection = DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}