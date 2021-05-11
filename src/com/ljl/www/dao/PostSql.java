package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.util.*;
import com.ljl.www.po.Post;
import com.ljl.www.view.Hint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

public class PostSql {
    public PostSql(){}

    /*static{
         createPaginationList();
    }*/

    static public PostListControlPacket createPaginationList(PostListControlPacket postListControlPacket){  //static public ObservableList createPaginationList()
        //ObservableList<Long> PaginationList= FXCollections.observableArrayList();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post ORDER BY post_id DESC";
            statement=connection.prepareStatement(sql);
            resultSets=statement.executeQuery();//这里不用参数
            /*int index=0;
            do{
                PaginationList.add(resultSets.getLong("post_id"));
                System.out.println(PaginationList.get(index++));
                for(int i=0;i<limit;i++){resultSets.next();}
            }while(resultSets.next());*/
                    System.out.println("done createPaginationList query here");
            int index=0;
            postListControlPacket.paginationList=new ArrayList<>();
            for(;resultSets.next();index++){
                if(index%postListControlPacket.limit==0){
                    postListControlPacket.paginationList.add(resultSets.getLong("post_id"));
                    System.out.println("PaginationList.get(i/limit)= "+postListControlPacket.paginationList.get(index/postListControlPacket.limit)+"i="+index);
                }
            }
            postListControlPacket.postCount=index;

        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        //return PaginationList;
        return  postListControlPacket;
    }

    static public PostListControlPacket setPaginationList(PostListControlPacket postListControlPacket){ //public ObservableList query(PostListControlPacket postListControlPacket)
        System.out.println("postListControlPacket.firstLogin= "+postListControlPacket.firstLogin);
        if(postListControlPacket.firstLogin==0){
            postListControlPacket.firstLogin++;
            postListControlPacket.paginationList=createPaginationList(postListControlPacket).paginationList;
        }
        //ObservableList<Post> postArrayList= FXCollections.observableArrayList();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE post_id <=? ORDER BY post_id DESC limit ?";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,postListControlPacket.paginationList.get(postListControlPacket.pageParam));
            statement.setLong(2,postListControlPacket.limit);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done  分页显示 query here");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();//666
            while(resultSets.next()){
/*                postArrayList.add(new Post());
                postArrayList.get(index).setPostId(resultSets.getLong("post_id"));
                postArrayList.get(index).setClientId(resultSets.getLong("client_id"));
                postArrayList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postArrayList.get(index).setPostTitle(resultSets.getString("post_title"));
                postArrayList.get(index).setPostArticle(resultSets.getString("post_article"));
                postArrayList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postArrayList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postArrayList.get(index).setRemarkCount(resultSets.getLong("remark_count"));*/

                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("client_id"));
                postListControlPacket.postList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postListControlPacket.postList.get(index).setPostTitle(resultSets.getString("post_title"));
                postListControlPacket.postList.get(index).setPostArticle(resultSets.getString("post_article"));
                postListControlPacket.postList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postListControlPacket.postList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postListControlPacket.postList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                index++;
            }
        }catch (SQLException e){
            //Hint.pop("空事件表！");
            System.out.println("Hint.pop(\"空事件表！\")");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Hint.pop(\"事件列表索引越界！\")");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        //return postArrayList;
        return postListControlPacket;
    }
    public Post updateMyPost(Post post){
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
    public Post newPost(Post post){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        Long tag=-9223372036854775808L;
        Post r4return=new Post();
        try{
            connection = DriverUtils.getConnection();

            String sql="INSERT INTO post(client_id,post_new_date,post_title,post_article,post_id)" +
                    " VALUES(?,?,?,?,?);";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,post.getClientId());
            statement.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
            statement.setString(3,post.getPostTitle());
            statement.setString(4,post.getPostArticle());
            do{
                    //r4return =String.valueOf(MyID.bit13$1());
                    //statement.setLong(3,Long.getLong(r4return));
                    tag= MyID.postID13$1BIT();
                    statement.setLong(5,tag);
                    //statement.setLong(3,MyID.bit13$1());
            }
            while(statement.executeUpdate()<=0);
            r4return=post;


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return r4return;
    }
    static public PostListControlPacket pulledPostList(PostListControlPacket postListControlPacket){  //static public ObservableList createPaginationList()
        //ObservableList<Long> PaginationList= FXCollections.observableArrayList();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE client_id=?";
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
            }
            postListControlPacket.postCount=index;

        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        //return PaginationList;
        return  postListControlPacket;
    }

}
