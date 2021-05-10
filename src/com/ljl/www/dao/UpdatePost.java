package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.po.Post;

import java.sql.*;

public class UpdatePost {
    public Post update(Post post){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Post r4return=new Post();
        try{
            connection = DriverUtils.getConnection();
            if(post.getPostId()>-99L) {
                String sql = "UPDATE POST SET " +
                        "post_title=?,post_article=?,post_new_date=?" +

                        " WHERE post_id=? ";
                statement=connection.prepareStatement(sql);
                statement.setString(1, post.getPostTitle());
                statement.setString(2, post.getPostArticle());
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                statement.setLong(4, post.getPostId());
                if(statement.executeUpdate()>0){
                    r4return =post;
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
