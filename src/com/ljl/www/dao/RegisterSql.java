/**
 * 登录查询
 */
package com.ljl.www.dao;

import com.ljl.www.util.MyID;

import java.sql.*;

public class RegisterSql{
    public long insert(String tel,String password){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        long tag=0L;
        try{
            connection = DriverUtils.getConnection();
            String sql0="SELECT * FROM client WHERE client_tel=?";
            statement=connection.prepareStatement(sql0);
            statement.setString(1,tel);
            resultSets=statement.executeQuery();//括号里面不能有语句
            if(!resultSets.next()){
                String sql="INSERT INTO client(client_tel,client_password,client_id) VALUES(?,?,?);";
                statement=connection.prepareStatement(sql);
                statement.setString(1,tel);
                statement.setString(2,password);
                do{
                    //r4return =String.valueOf(MyID.bit13$1());
                    //statement.setLong(3,Long.getLong(r4return));
                    tag=MyID.bit13$1();
                    statement.setLong(3,tag);
                    //statement.setLong(3,MyID.bit13$1());
                }
                while(statement.executeUpdate()<=0);
            }
            /*int count=statement.executeUpdate();
            if(count>0){
                tag=true;
                System.out.println(resultSets.getString("client_password"));
            }*/
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return tag;
    }
}

