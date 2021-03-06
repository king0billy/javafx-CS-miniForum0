/**
 * 提供静态方法块获取properties文件数据，getConnection链接数据库，release释放
 */
package com.ljl.www.dao;

import com.ljl.www.po.Client;
import com.ljl.www.po.*;
import com.ljl.www.util.PostListControlPacket;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @className DBServer
 * @description 进一步封装查询类
 * @author  22427(king0liam)
 * @date 2021/6/18 14:19
 * @version 1.0
 * @since version-0.0
 */

public class DBServer {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    public DBServer(){
        //注册驱动（但是最新的mysql驱动似乎不需要这一步）
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
    public PostListControlPacket getTopList(PostListControlPacket postListControlPacket){
        return  PostSql.setTopList(postListControlPacket);
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
    public Post deletePost(Post post){
        return new PostSql().deletePost(post);
    }
    public Post updatePostLevel(Post post){ return new PostSql().updatePostLevel(post); }

    public ThumbsUp showThumbsUp(ThumbsUp thumbsUp){
        return new ThumbsUpSql().showThumbsUp(thumbsUp);
    }
    public ThumbsUp addThumbsUp(ThumbsUp thumbsUp){
        return new ThumbsUpSql().addThumbsUp(thumbsUp);
    }
    public ThumbsUp deleteThumbsUp(ThumbsUp thumbsUp){
        return new ThumbsUpSql().deleteThumbsUp(thumbsUp);
    }

    public PostListControlPacket getPulledList(PostListControlPacket postListControlPacket){
        return  PostSql.pulledPostList(postListControlPacket);
    }
    public PostListControlPacket getThumbsUpList(PostListControlPacket postListControlPacket){return new ThumbsUpSql().getThumbsUpList(postListControlPacket);};
    public PostListControlPacket getMyCommentList(PostListControlPacket postListControlPacket){return new RemarkSql().getMyCommentList(postListControlPacket);};

    public ArrayList<Remark> getRemarkList(ArrayList<Remark> remarkList){return new RemarkSql().queryRemark(remarkList);};

    public Remark insertRemark(Remark remark){return new RemarkSql().insertRemark(remark);}
    public Remark editRemark(Remark remark){return new RemarkSql().editRemark(remark);}
    public Remark deleteRemark(Remark remark){return new RemarkSql().deleteRemark(remark);}

}
