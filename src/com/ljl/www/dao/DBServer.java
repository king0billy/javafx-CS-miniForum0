/**
 * 提供静态方法块获取properties文件数据，getConnection链接数据库，release释放
 */
package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.po.*;
import com.ljl.www.util.PostListControlPacket;

import java.io.InputStream;
import java.sql.*;
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
        return  new ClientSql().registerInsert(client);
    }
    public Client loginClient(Client client){
        return  new ClientSql().loginQuery(client);
    }
    public PostListControlPacket setPaginationList(PostListControlPacket postListControlPacket){
        return PostSql.createPaginationList(postListControlPacket);
    }
    public PostListControlPacket getPostListView(PostListControlPacket postListControlPacket){
        return  PostSql.setPaginationList(postListControlPacket);
    }
    public Client updateMyInfo(Client client){
        return new ClientSql().myInfoUpdate(client);
    }
    public Client authorQuery(Client client){
        return new ClientSql().authorQuery(client);
    }
    public Post updateMyPostInfo(Post post){
        return new PostSql().updateMyPost(post);
    }
    public Post newPost(Post post){
        return new PostSql().newPost(post);
    }
    public PostListControlPacket getPulledList(PostListControlPacket postListControlPacket){
        return  PostSql.pulledPostList(postListControlPacket);
    }

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
