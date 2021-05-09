package com.ljl.www.util;

public  class MyID {
    public static Long bit13$1(){
        return Long.parseLong(System.currentTimeMillis()+""+(long)(Math.random()*10));
    }

    /*public static void main(String[] args) {
        System.out.println(new MyID().myId());
        System.out.println(System.currentTimeMillis());
    }*/
}
