package com.ljl.www.service.mainlogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainApp {
    public static void main(String[] args){
        try {
            ServerSocket listen=new ServerSocket(6000);                   //监听socket

            while(true){

                Socket ss=listen.accept();                                //监听是否有连接
                new AppThread(ss).start();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
