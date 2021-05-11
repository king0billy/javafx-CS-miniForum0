package com.ljl.www.util;

import com.ljl.www.po.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class PostListControlPacket implements Serializable {
    public StringBuffer operation=new StringBuffer();//昨天老师才讲完，stringBuffer没有重新写equals。。。
    public Integer limit=2;
    //public ObservableList<Long> paginationList= FXCollections.observableArrayList();
    //public ObservableList<Post> postList=FXCollections.observableArrayList();
    public ArrayList<Long> paginationList=new ArrayList<>();
    public ArrayList<Post> postList=new ArrayList<>();
    public  Integer postListSelectedIndex=-9999;
    public  Integer firstLogin=0;
    public  Integer pageParam=0;
    public  Integer postCount=4;
    public  Long clientId=-99L;
    public PostListControlPacket(){}
}
