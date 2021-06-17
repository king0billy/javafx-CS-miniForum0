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
 * @date 2021/5/12 15:21
 * @version 1.0
 * @since version-0.0
 */
public class PostListControlPacket implements Serializable {
    public StringBuffer operation=new StringBuffer();//昨天老师才讲完，stringBuffer没有重新写equals。。。
    public Integer limit=5;//5要和NumberChoice默认值配合好
    public ArrayList<Long> paginationList=new ArrayList<>();
    public ArrayList<Post> postList=new ArrayList<>();
    public  Integer postListSelectedIndex=-9999;
    public  Integer firstLogin=0;
    public  Integer pageParam=0;
    public  Integer postCount=4;
    public  Long clientId=-99L;
    public PostListControlPacket(){}
}
