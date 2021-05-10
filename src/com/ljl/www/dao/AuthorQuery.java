package com.ljl.www.dao;

import com.ljl.www.po.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorQuery {
    public Client query(Client client) {//public Client query(String id,String password)
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
}
