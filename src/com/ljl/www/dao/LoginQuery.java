/**
 * 登录查询
 */
package com.ljl.www.dao;

import com.ljl.www.po.Client;

import java.sql.*;

public class LoginQuery{
    public boolean query(Client client){
        if(client.getClientTel().equals("")){
                query(client.getClientId().toString(),client.getClientPassword());
        }
    }
    public boolean query(String id,String password){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        boolean tag=false;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM CLIENT WHERE client_id=? AND client_password=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,password);
            //resultSets=statement.executeQuery();
            while(resultSets.next()){
                tag=true;
                System.out.println(resultSets.getString("client_password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return tag;
    }
    public boolean queryTel(String tel,String password){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        boolean tag=false;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM CLIENT WHERE client_tel=? AND client_password=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,tel);
            statement.setString(2,password);
            resultSets=statement.executeQuery();
            while(resultSets.next()){
                tag=true;
                System.out.println(resultSets.getString("client_password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return tag;
    }
}
