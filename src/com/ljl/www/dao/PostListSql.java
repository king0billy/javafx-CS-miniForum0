package com.ljl.www.dao;

import com.ljl.www.util.*;
import com.ljl.www.po.Post;
import com.ljl.www.view.Hint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

public class PostListSql {
    public PostListSql(){}

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
            int i=0;
            for(;resultSets.next();i++){
                if(i%postListControlPacket.limit==0){
                    postListControlPacket.paginationList.add(resultSets.getLong("post_id"));
                    System.out.println("PaginationList.get(i/limit)="+postListControlPacket.paginationList.get(i/postListControlPacket.limit)+"i="+i);
                }
            }
            postListControlPacket.postCount=i;

        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        //return PaginationList;
        return  postListControlPacket;
    }

    static public PostListControlPacket query(PostListControlPacket postListControlPacket){ //public ObservableList query(PostListControlPacket postListControlPacket)
        if(postListControlPacket.firstLogin==0){
            postListControlPacket.firstLogin++;
            createPaginationList(postListControlPacket);
        }
        //ObservableList<Post> postArrayList= FXCollections.observableArrayList();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE post_id <? ORDER BY post_id DESC limit ?";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,postListControlPacket.paginationList.get(postListControlPacket.pageParam));
            statement.setLong(2,postListControlPacket.limit);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done  query query here");
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

}
