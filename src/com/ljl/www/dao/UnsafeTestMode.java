package com.ljl.www.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnsafeTestMode {
    public  static void main(String[]args){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            statement=connection.createStatement();
            String sql="";
            /*int i=0;
            //i=statement.executeUpdate();
            if(i>0){
                System.out.println("");
            }*/

            /*while(resultSets.next()){
                System.out.println(resultSets.getString());
            }*/
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
    }
}
