/**
 * 登录查询
 */
package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.util.MyID;

import java.sql.*;

public class ClientSql {
    /*public Client query(Client client){//优先电话登录
        if(client.getClientTel().equals("")){
               return query(client.getClientId().toString(),client.getClientPassword());
        }
        else return queryTel(client.getClientTel(),client.getClientPassword());
    }*/
    public Client myInfoUpdate(Client client){
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
    public Client authorQuery(Client client) {//public Client query(String id,String password)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSets = null;


        Client newClient = new Client();
        newClient.setClientId(client.getClientId());
        newClient.setClientTel(client.getClientTel());
        newClient.setClientPassword(client.getClientPassword());
        newClient.setClientEnrollDate(client.getClientEnrollDate());

        try {
            connection = DriverUtils.getConnection();

            String sql = "SELECT * FROM CLIENT WHERE client_id=?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, client.getClientId());

            resultSets = statement.executeQuery();
            while (resultSets.next()) {

                newClient.setClientId(resultSets.getLong("client_id"));
                newClient.setClientTel(resultSets.getString("client_tel"));

                newClient.setClientNickname(resultSets.getString("client_nickname"));
                newClient.setClientSex(resultSets.getString("client_sex"));
                newClient.setClientAddress(resultSets.getString("client_address"));
                newClient.setClientDescription(resultSets.getString("client_description"));
                newClient.setClientEnrollDate(resultSets.getTimestamp("client_enroll_date"));
                newClient.setClientPrivilege(resultSets.getLong("client_privilege"));


                System.out.println("password=" + resultSets.getString("client_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DriverUtils.release(connection, statement, resultSets);
        }
        return newClient;
    }
    public Client loginQuery(Client client){//public Client query(String id,String password)
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        
        
        Client newClient=new Client();
        newClient.setClientId(client.getClientId());
        newClient.setClientTel(client.getClientTel());
        newClient.setClientPassword(client.getClientPassword());
        newClient.setClientEnrollDate(client.getClientEnrollDate());

        try{
            connection = DriverUtils.getConnection();
            if(client.getClientId()>-99L){
                String sql="SELECT * FROM CLIENT WHERE client_id=? AND client_password=?";
                statement=connection.prepareStatement(sql);
                statement.setLong(1,client.getClientId());
            }
            else{
                String sql="SELECT * FROM CLIENT WHERE client_tel=? AND client_password=?";
                statement=connection.prepareStatement(sql);
                statement.setString(1,client.getClientTel());
            }

            statement.setString(2,client.getClientPassword());
            resultSets=statement.executeQuery();
            while(resultSets.next()){

                newClient.setClientId(resultSets.getLong("client_id"));
                newClient.setClientTel(resultSets.getString("client_tel"));

                newClient.setClientNickname(resultSets.getString("client_nickname"));
                newClient.setClientSex(resultSets.getString("client_sex"));
                newClient.setClientAddress(resultSets.getString("client_address"));
                newClient.setClientDescription(resultSets.getString("client_description"));
                newClient.setClientEnrollDate(resultSets.getTimestamp("client_enroll_date"));
                newClient.setClientPrivilege(resultSets.getLong("client_privilege"));

/*              System.out.println("client.getClientAddress()==null"+(client.getClientAddress().equals(null)));
                System.out.println("client.getClientAddress()==null"+(client.getClientAddress()==null)+"");
                System.out.println("client.getClientDescription()==null"+(client.getClientDescription()==null)+"");*/
                System.out.println("password="+resultSets.getString("client_password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return newClient;
    }
    public Long registerInsert(Client client){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Long tag=-9223372036854775808L;
        try{
            connection = DriverUtils.getConnection();
            String sql0="SELECT * FROM client WHERE client_tel=?";
            statement=connection.prepareStatement(sql0);
            statement.setString(1,client.getClientTel());
            resultSets=statement.executeQuery();//括号里面不能有语句
            if(!resultSets.next()){
                String sql="INSERT INTO client(client_tel,client_password,client_id,client_enroll_date,client_privilege) VALUES(?,?,?,?,?);";
                statement=connection.prepareStatement(sql);
                statement.setString(1,client.getClientTel());
                statement.setString(2,client.getClientPassword());
                statement.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
                statement.setLong(5,client.getClientPrivilege());
                do{
                    //r4return =String.valueOf(MyID.bit13$1());
                    //statement.setLong(3,Long.getLong(r4return));
                    tag= MyID.clientID13$1BIT();
                    statement.setLong(3,tag);
                    //statement.setLong(3,MyID.bit13$1());
                }
                while(statement.executeUpdate()<=0);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return tag;
    }


    /*public Client queryTel(String tel,String password){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Client client=new Client();
        try{

            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM CLIENT WHERE client_tel=? AND client_password=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,tel);
            statement.setString(2,password);
            resultSets=statement.executeQuery();
            while(resultSets.next()){
                client.setClientId(resultSets.getLong("client_id"));
                client.setClientTel(resultSets.getString("client_tel"));

                client.setClientNickname(resultSets.getString("client_nickname"));
                client.setClientSex(resultSets.getString("client_sex"));
                client.setClientAddress(resultSets.getString("client_address"));
                client.setClientDescription(resultSets.getString("client_description"));
                client.setClientEnrollDate(resultSets.getTimestamp("client_enroll_date"));
                client.setClientPrivilege(resultSets.getLong("client_privilege"));

                System.out.println(resultSets.getString("client_password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return client;
    }*/
}
