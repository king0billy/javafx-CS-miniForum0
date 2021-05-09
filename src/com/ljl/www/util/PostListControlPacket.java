package com.ljl.www.util;

import com.ljl.www.po.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class PostListControlPacket implements Serializable {
    public StringBuffer operation=new StringBuffer();//昨天老师才讲完，stringBuffer没有重新写buffer。。。
    public Integer limit=2;
    //public ObservableList<Long> paginationList= FXCollections.observableArrayList();
    //public ObservableList<Post> postList=FXCollections.observableArrayList();
    public ArrayList<Long> paginationList=new ArrayList<>();
    public ArrayList<Post> postList=new ArrayList<>();
    public  int firstLogin=0;
    public  int pageParam=0;
    public  int postCount=4;
    public PostListControlPacket(){}
}
