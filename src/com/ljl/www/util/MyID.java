package com.ljl.www.util;
/**
 * @className MyID
 * @description 简单山寨一下snowflake生成各主键的丐版算法
 * @author  22427(king0liam)
 * @date 2021/6/18 15:20
 * @version 1.0
 * @since version-0.0
 */
public  class MyID {
    public static Long clientID13$1BIT(){
        return Long.parseLong(System.currentTimeMillis()+""+(long)(Math.random()*10));
    }
    public static Long postID13$1BIT(){
        return Long.parseLong(System.currentTimeMillis()+"0"+(long)(Math.random()*10));//0 is post
    }
    public static Long commentID13$1BIT(){
        return Long.parseLong(System.currentTimeMillis()+"1"+(long)(Math.random()*10));//1 is comment
    }


    /*public static void main(String[] args) {
        System.out.println(new MyID().myId());
        System.out.println(System.currentTimeMillis());
    }*/
}
