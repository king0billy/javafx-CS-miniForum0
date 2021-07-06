package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.po.ThumbsUp;
import com.ljl.www.service.mainlogic.MainApp;
import com.ljl.www.util.*;
import com.ljl.www.po.Post;
import com.ljl.www.view.Hint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
/**
 * @className PostSql
 * @description 和用户有关的数据库语句，内部方法接受的参数统一为数据库实体类po下的Post,
 * @author  22427(king0liam)
 * @date 2021/6/18 14:36
 * @version 1.0
 * @since version-0.0
 */

public class PostSql {
    public PostSql(){}

    static public PostListControlPacket createPaginationList(PostListControlPacket postListControlPacket){
        /**
         * @description 查询一次事件全表，并根据每页限制和降序为事件id建立一个索引数组
         * @exception SQLException
         * @param [postListControlPacket]
         * @return [com.ljl.www.util.PostListControlPacket]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:38
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post where visible!=0 ORDER BY post_id DESC";
            //String sql="SELECT * FROM post where visible!=0 ORDER BY post_id ";
            statement=connection.prepareStatement(sql);
            resultSets=statement.executeQuery();//这里不用参数

                    System.out.println("done createPaginationList query here");
            int index=0;
            postListControlPacket.paginationList=new ArrayList<>();
            for(;resultSets.next();index++){
                    postListControlPacket.paginationList.add(resultSets.getLong("post_id"));
            }
            postListControlPacket.postCount=index;

        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return  postListControlPacket;
    }

    static public PostListControlPacket setPaginationList(PostListControlPacket postListControlPacket){ 
        /**
         * @description 每次切换分页都要调用这个，根据扫描出来的索引数组不用查询整张表而是部分
         * @exception SQLException       
         * @param [postListControlPacket]
         * @return [com.ljl.www.util.PostListControlPacket]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:44
         */
        System.out.println("postListControlPacket.firstLogin= "+postListControlPacket.firstLogin);
        //postListControlPacket.firstLogin++;
        if(postListControlPacket.firstLogin==0){
            postListControlPacket.firstLogin++;
            postListControlPacket.paginationList=createPaginationList(postListControlPacket).paginationList;
        }
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE post_id <=? and visible!=0 ORDER BY post_id DESC limit ?";
            statement=connection.prepareStatement(sql);
            //statement.setLong(1,postListControlPacket.paginationList.get(postListControlPacket.pageParam*postListControlPacket.limit));
            statement.setLong(1,postListControlPacket.paginationList.get(postListControlPacket.pageParam*postListControlPacket.limit));
            statement.setLong(2,postListControlPacket.limit);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done  分页显示 query here");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();//666
            while(resultSets.next()){
                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("client_id"));
                postListControlPacket.postList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postListControlPacket.postList.get(index).setPostTitle(resultSets.getString("post_title"));
                postListControlPacket.postList.get(index).setPostArticle(resultSets.getString("post_article"));
                postListControlPacket.postList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postListControlPacket.postList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postListControlPacket.postList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                postListControlPacket.postList.get(index).setVisible(resultSets.getByte("visible"));
                index++;
            }
        }catch (SQLException e){
            System.out.println("Hint.pop(空事件表)");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Hint.pop(\"事件列表索引越界\")");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return postListControlPacket;
    }
    static public PostListControlPacket setTopList(PostListControlPacket postListControlPacket){
        /**
         * @description 每次切换分页都要调用这个，根据扫描出来的索引数组不用查询整张表而是部分
         * @exception SQLException
         * @param [postListControlPacket]
         * @return [com.ljl.www.util.PostListControlPacket]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:44
         */
        if(postListControlPacket.firstLogin==0){
            postListControlPacket.firstLogin++;
            postListControlPacket.paginationList=createPaginationList(postListControlPacket).paginationList;
        }
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE visible>=2 ORDER BY visible DESC, post_id DESC";
            statement=connection.prepareStatement(sql);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done  分页显示 query here");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();//666
            while(resultSets.next()){
                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("client_id"));
                postListControlPacket.postList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postListControlPacket.postList.get(index).setPostTitle(resultSets.getString("post_title"));
                postListControlPacket.postList.get(index).setPostArticle(resultSets.getString("post_article"));
                postListControlPacket.postList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postListControlPacket.postList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postListControlPacket.postList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                postListControlPacket.postList.get(index).setVisible(resultSets.getByte("visible"));
                index++;
            }
        }catch (SQLException e){
            System.out.println("Hint.pop(空事件表)");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Hint.pop(\"事件列表索引越界\")");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return postListControlPacket;
    }
    public Post updateMyPost(Post post){
        /**
         * @description 更新已有事件
         * @exception SQLException       
         * @param [post]
         * @return [com.ljl.www.po.Post]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:45
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Post r4return=new Post();
        r4return.setClientId(-99L);
        try{
            connection = DriverUtils.getConnection();
            if(post.getPostId()>-99L) {
                String sql = "UPDATE POST SET " +
                        "post_title=?,post_article=?,post_new_date=?" +

                        " WHERE post_id=? and visible!=0";
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
    public Post newPost(Post post){
        /**
         * @description 新建事件并插入
         * @exception SQLException       
         * @param [post]
         * @return [com.ljl.www.po.Post]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:46
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Long tag=-9223372036854775808L;
        Post r4return=new Post();
        try{
            connection = DriverUtils.getConnection();

            String sql="INSERT INTO post(client_id,post_new_date,post_title,post_article,post_id,visible)" +
                    " VALUES(?,?,?,?,?,true);";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,post.getClientId());
            statement.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
            statement.setString(3,post.getPostTitle());
            statement.setString(4,post.getPostArticle());
            do{
                    tag= MyID.postID13$1BIT();
                    statement.setLong(5,tag);
            }
            while(statement.executeUpdate()<=0);
            r4return=post;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }

        //MainApp.list8TimeDESC.paginationList.add(tag);

        return r4return;
    }
    static public PostListControlPacket pulledPostList(PostListControlPacket postListControlPacket){
        /**
         * @description 查询登录此账号用户发过的所有帖子
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
            String sql="SELECT * FROM post WHERE client_id=? and visible!=0 ORDER BY post_id DESC" ;
            statement=connection.prepareStatement(sql);
            statement.setLong(1,postListControlPacket.clientId);
            resultSets=statement.executeQuery();//这里不用参数

            System.out.println("done pulledPostList");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();
            for(;resultSets.next();index++){
                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("client_id"));
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
    public Post deletePost(Post post){
        /**
         * @description 更新已有事件
         * @exception SQLException
         * @param [post]
         * @return [com.ljl.www.po.Post]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:45
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Post r4return=new Post();
        r4return.setClientId(-99L);
        try{
            connection = DriverUtils.getConnection();
            if(post.getPostId()>-99L) {
                String sql = "UPDATE POST SET " +
                        "visible=?,post_new_date=?" +
                        " WHERE post_id=? and visible!=0";
                statement=connection.prepareStatement(sql);
                statement.setInt(1, 0);
                statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                statement.setLong(3, post.getPostId());
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
    public Post updatePostLevel(Post post){
        /**
         * @description 更新已有事件
         * @exception SQLException
         * @param [post]
         * @return [com.ljl.www.po.Post]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:45
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Post r4return=new Post();
        r4return.setClientId(-99L);
        try{
            connection = DriverUtils.getConnection();
            if(post.getPostId()>-99L) {
                String sql = "UPDATE POST SET " +
                        "visible=?" +
                        " WHERE post_id=? and visible!=0";
                statement=connection.prepareStatement(sql);
                statement.setInt(1, post.getVisible());
                statement.setLong(2, post.getPostId());
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
