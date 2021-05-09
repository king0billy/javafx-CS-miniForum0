/**
 * 提供静态方法块获取properties文件数据，getConnection链接数据库，release释放
 */
package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.po.*;
import com.ljl.www.util.PostListControlPacket;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Observable;
import java.util.Properties;


public class DBServer {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    public static Client client;
    public static Favorite favorite;
    public static Post post;
    public static Remark remark;
    public static Report report;
    public static ThumbsUp thumbsUp;


    public DBServer(){
        try{
            InputStream input =DriverUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(input);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            DriverManager.getConnection(url,username,password);

            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Long insertClient(Client client){
        return  new RegisterSql().insert(client);
    }
    public Client loginClient(Client client){
        return  new LoginQuery().query(client);
    }
    public PostListControlPacket setPaginationList(PostListControlPacket postListControlPacket){
        return PostListSql.createPaginationList(postListControlPacket);}
    public PostListControlPacket getPostListView(PostListControlPacket postListControlPacket){
        return  PostListSql.query(postListControlPacket);}



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
