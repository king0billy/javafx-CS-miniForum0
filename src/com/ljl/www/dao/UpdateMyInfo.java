package com.ljl.www.dao;

import com.ljl.www.po.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateMyInfo {
    public Client update(Client client){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Client r4return=new Client();
        try{
            connection = DriverUtils.getConnection();
            if(client.getClientId()>-99L) {
                String sql = "UPDATE CLIENT SET " +
                        "client_tel=?,client_password=?,client_nickname=?," +
                        "client_address=?,client_description=?,client_sex=?" +
                        " WHERE client_id=? ";
                statement=connection.prepareStatement(sql);
                statement.setString(1, client.getClientTel());
                statement.setString(2, client.getClientPassword());
                statement.setString(3, client.getClientNickname());
                statement.setString(4, client.getClientAddress());
                statement.setString(5, client.getClientDescription());
                statement.setString(6, client.getClientSex());
                statement.setLong(7, client.getClientId());
                if(statement.executeUpdate()>0){
                    r4return =client;
                };
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return r4return;
    }
}
