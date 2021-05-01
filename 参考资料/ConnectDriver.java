/**
 * 此类学习用，没有封装，作废
 */
package com.ljl.www.dao;


import java.sql.*;


public class ConnectDriver {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.jdbc.Driver");
         String url="jdbc:mysql://localhost:3306/book?useUnicode=True&characterEncoding=utf8&useSSL=true";
         String username="root";
         String password="123456";

         Connection connection = DriverManager.getConnection(url,username,password);

         /*connection.rollback();
         connection.commit();*/
         //connection.setAutoCommit() ;

         Statement statement = connection.createStatement();
         String sql="SELECT * FROM  course";
         ResultSet resultSet=statement.executeQuery(sql);

         /*
         statement.execute();//基本不用？
         statement.executeQuery();//查
         statement.executeUpdate();//增删改
         statement.executeBatch();//不作要求
         */
          /*resultSet.beforeFirst();
          resultSet.afterLast();
          resultSet.next();
          resultSet.previous();//前一行
          resultSet.absolute();//指定行
          */

         while(resultSet.next()){
             System.out.println("id="+resultSet.getObject("CNO"));
         }
         resultSet.close();
         statement.close();
         connection.close();
   }
}
