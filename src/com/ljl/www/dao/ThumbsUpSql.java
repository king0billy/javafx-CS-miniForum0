package com.ljl.www.dao;

import com.ljl.www.po.Post;
import com.ljl.www.po.ThumbsUp;
import com.ljl.www.util.MyID;
import com.ljl.www.util.PostListControlPacket;
import com.ljl.www.view.Hint;

import java.sql.*;
import java.util.ArrayList;

/**
 * @version 1.0
 * @ClassName: ThumbsUpSql
 * @Description
 * @Author 22427(king0liam)
 * @Date: 2021/6/19 10:21
 * @since version-0.0
 */
public class ThumbsUpSql {
    ThumbsUpSql(){}
    static public ThumbsUp showThumbsUp(ThumbsUp  thumbsUp) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        ThumbsUp r4return=new ThumbsUp();
        r4return.setClientId(-99L);
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM thumbs_up WHERE client_id=? AND post_id=? and visible!=0;" ;
            statement=connection.prepareStatement(sql);
            statement.setLong(1,thumbsUp.getClientId());
            statement.setLong(2,thumbsUp.getPostId());
            resultSets=statement.executeQuery();//这里不用参数

            for(;resultSets.next();){
                if(resultSets.getInt("visible")!=0){
                    r4return=thumbsUp;
                }
                else{//if(resultSets.getInt("visible")==0)

                }
            }
            System.out.println("done showThumbsUp");
        }catch (SQLException e){
            System.out.println("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return  r4return ;
    }
    static public ThumbsUp addThumbsUp(ThumbsUp  thumbsUp) {
        Connection connection=null;
        PreparedStatement statement=null;
        PreparedStatement statementPost=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="INSERT INTO thumbs_up(post_id,client_id,thumbs_up_new_date,visible)" +
                    " VALUES(?,?,?,?)"+"ON DUPLICATE KEY UPDATE post_id=?,client_id=?,thumbs_up_new_date=?,visible=?;";
            Timestamp tempTime=new Timestamp(System.currentTimeMillis());
            statement=connection.prepareStatement(sql);
            statement.setLong(1,thumbsUp.getPostId());
            statement.setLong(2,thumbsUp.getClientId());
            statement.setTimestamp(3,tempTime);
            statement.setBoolean(4,true);
            statement.setLong(5,thumbsUp.getPostId());
            statement.setLong(6,thumbsUp.getClientId());
            statement.setTimestamp(7,tempTime);
            statement.setBoolean(8,true);

            String sqlPost="UPDATE POST SET " +
                    "thumbs_up_count=thumbs_up_count+1" +
                    " WHERE post_id=? and visible!=0;";
            statementPost=connection.prepareStatement(sqlPost);
            statementPost.setLong(1,thumbsUp.getPostId());
            if(statement.executeUpdate()>0&&statementPost.executeUpdate()>0){
                  thumbsUp.setThumbsUpNewDate(tempTime);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return thumbsUp;
    }
    static public ThumbsUp deleteThumbsUp(ThumbsUp  thumbsUp) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        PreparedStatement statementPost=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="UPDATE thumbs_up SET " +
                    "visible=?,thumbs_up_drop_date=?"+
                    " WHERE post_id=? AND client_id=?;";
            Timestamp tempTime=new Timestamp(System.currentTimeMillis());
            statement=connection.prepareStatement(sql);
            statement.setInt(1,0);
            statement.setTimestamp(2,tempTime);
            statement.setLong(3,thumbsUp.getPostId());
            statement.setLong(4,thumbsUp.getClientId());

            String sqlPost="UPDATE POST SET " +
                    "thumbs_up_count=thumbs_up_count-1" +
                    " WHERE post_id=? and visible!=0;";
            statementPost=connection.prepareStatement(sqlPost);
            statementPost.setLong(1,thumbsUp.getPostId());
            if(statement.executeUpdate()>0&&statementPost.executeUpdate()>0){
                thumbsUp.setThumbsUpDropDate(tempTime);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return thumbsUp;
    }
    static public PostListControlPacket getThumbsUpList(PostListControlPacket postListControlPacket){
        /**
         * @description 查询登录此账号用户点赞过的所有帖子
         * @exception
         * @param [com.ljl.www.util.PostListControlPacket] [postListControlPacket]
         * @return [com.ljl.www.util.PostListControlPacket]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:49
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post inner join thumbs_up ON thumbs_up.client_id=? and post.visible!=0 and thumbs_up.visible!=0 and thumbs_up.post_id=post.post_id ORDER BY post.post_id DESC" ;
            statement=connection.prepareStatement(sql);
            statement.setLong(1,postListControlPacket.clientId);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done getThumbsUpList");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();
            for(;resultSets.next();index++){
                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post.post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("post.client_id"));
                postListControlPacket.postList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postListControlPacket.postList.get(index).setPostTitle(resultSets.getString("post_title"));
                postListControlPacket.postList.get(index).setPostArticle(resultSets.getString("post_article"));
                postListControlPacket.postList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postListControlPacket.postList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postListControlPacket.postList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                postListControlPacket.postList.get(index).setVisible(resultSets.getByte("visible"));
            }
            postListControlPacket.postCount=index;

        }catch (SQLException e){
            System.out.println("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return  postListControlPacket;
    }
/*    static  private Boolean select(ThumbsUp  thumbsUp){//没有必要了
        return false;
    }*/
}
