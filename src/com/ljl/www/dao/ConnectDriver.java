package com.ljl.www.dao;


import java.sql.*;

public class ConnectDriver {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.jdbc.Driver");
         String url="jdbc:mysql://localhost:3306/book?useUnicode=True&characterEncoding=utf8&useSSL=true";
         String username="root";
         String password="123456";

         Connection connection = DriverManager.getConnection(url,username,password);

         Statement statement = connection.createStatement();
         String sql="SELECT * FROM  course";
         ResultSet resultSet=statement.executeQuery(sql);

         while(resultSet.next()){
             System.out.println("id="+resultSet.getObject("CNO"));
         }
         resultSet.close();
         statement.close();
         connection.close();
   }
}
