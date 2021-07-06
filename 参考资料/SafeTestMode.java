/**
 * 对课本例题表的测试
 */
package com.ljl.www.dao;

import java.sql.*;

public class SafeTestMode {
    public  static void main(String[]args){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();

            String sql="INSERT INTO STUDENT(SNO, SNAME, SSEX, SAGE, SDEPT) VALUES(?,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            //statement.setInt(1,1);
            statement.setString(1,"119009999");
            statement.setString(2,"nothing");
            statement.setString(3," 男");
            statement.setInt(4,19);
            statement.setString(5," CS");
            //statement.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
            //int i=0;
            int i=statement.executeUpdate();
            if(i>0){
                System.out.println("成功");
            }

            /*
            String sql="SELECT * FROM STUDENT WHERE SNO=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,"119009999");
            resultSets=statement.executeQuery();
            while(resultSets.next()){
                System.out.println(resultSets.getString("SNAME"));
             */

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
    }
}
