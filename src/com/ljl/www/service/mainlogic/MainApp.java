package com.ljl.www.service.mainlogic;

import com.ljl.www.dao.PostSql;
import com.ljl.www.util.PostListControlPacket;
import com.ljl.www.view.PostDetailPage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @className MainApp
 * @description 服务端主程序
 * @author  22427(king0liam)
 * @date 2021/5/12 15:11
 * @version 1.0
 * @since version-0.0
 * @see Thread
 */

public class MainApp {
    //public static PostListControlPacket chaos=new PostListControlPacket();
    public static void main(String[] args){
        /**
         * @description 服务端主函数
         * @exception IOException
         * @param [java.lang.String[]] [args]
         * @return [java.lang.String[]]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:12
         */
        try {

            //PostSql.createPaginationList(chaos);//this is for Pagination!;

            //监听socket
            ServerSocket listen=new ServerSocket(6666);
            while(true){
                //监听是否有连接
                Socket ss=listen.accept();
                //开启线程
                new AppThread(ss).start();
            }
        } catch (IOException e) {
            /// TODO 注销登录和意外退出需要关闭线程!
            e.printStackTrace();
        }
    }
}
