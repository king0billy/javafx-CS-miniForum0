package com.ljl.www.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DriverUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    static{
        try{
            InputStream input =DriverUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(input);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");

            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
