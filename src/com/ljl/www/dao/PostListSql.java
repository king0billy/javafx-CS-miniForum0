package com.ljl.www.dao;

import com.ljl.www.po.Post;
import com.ljl.www.view.Hint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

public class PostListSql {
    static Integer limit=2;
    static ObservableList<Long> PaginationList;
    public static int firstLogin=0;
    public static int postCount=4;

    public PostListSql(){}

    static{
         createPaginationList();
    }

    public static void setLimit(Integer limit){
        PostListSql.limit=limit;
    }
    public static Integer getLimit(){
        return PostListSql.limit;
    }
    public ObservableList query(Integer pageParam,Integer limit){
        if(firstLogin==0){
            firstLogin++;
            PaginationList=createPaginationList();
        }
        ObservableList<Post> postArrayList= FXCollections.observableArrayList();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM post WHERE post_id <? ORDER BY post_id DESC limit ?";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,PaginationList.get(pageParam));
            statement.setLong(2,limit);
            resultSets=statement.executeQuery();//这里不用参数
            int index=0;
            while(resultSets.next()){
                postArrayList.add(new Post());
                postArrayList.get(index).setPostId(resultSets.getLong("post_id"));
                postArrayList.get(index).setClientId(resultSets.getLong("client_id"));
                postArrayList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postArrayList.get(index).setPostTitle(resultSets.getString("post_title"));
                postArrayList.get(index).setPostArticle(resultSets.getString("post_article"));
                postArrayList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postArrayList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postArrayList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                index++;
            }
        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return postArrayList;
    }
    static public ObservableList createPaginationList(){
        ObservableList<Long> PaginationList= FXCollections.observableArrayList();
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
            int i=0;
            for(;resultSets.next();i++){
                if(i%limit==0){
                    PaginationList.add(resultSets.getLong("post_id"));
                    System.out.println("PaginationList.get(i/4)="+PaginationList.get(i/limit)+"i="+i);
                }
            }
            postCount=i;

        }catch (SQLException e){
            Hint.pop("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return PaginationList;
    }
}
