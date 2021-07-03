package com.ljl.www.util;

import com.ljl.www.po.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @className PostListControlPacket
 * @description 封装好的传输控制包,以便传输各种Post相关的信息
 * @author  22427(king0liam)
 * @date 2021/6/18 15:21
 * @version 1.0
 * @since version-0.0
 */
public class PostListControlPacket implements Serializable {
    public StringBuffer operation=new StringBuffer();//昨天老师才讲完，stringBuffer没有重新写equals。。。
    public Integer limit=5;//5要和NumberChoice默认值配合好
    public ArrayList<Long> paginationList=new ArrayList<>();//没必要搞这个!!传输的代价特别大
    public ArrayList<Post> postList=new ArrayList<>();//虽然必要,但是传输的代价也比较大,因为要出传全文
    public  Integer postListSelectedIndex=-9999;
    public  Integer firstLogin=0;//原本是为了客户端查看是不是首次登录
    public  Integer pageParam=0;//第几页of Pagination
    public  Integer postCount=4;//和limit不一样,库里总共多少个帖子
    public  Long clientId=-99L;
    public PostListControlPacket(){}
}
