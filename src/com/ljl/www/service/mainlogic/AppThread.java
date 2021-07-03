package com.ljl.www.service.mainlogic;

import java.net.Socket;

/**
 * @className AppThread
 * @description 线程,调用主逻辑类
 * @author  22427(king0liam)
 * @date 2021/6/18 15:14
 * @version 1.0
 * @since version-0.0
 */

public class AppThread extends Thread{
    public Socket ss;
    AppThread(Socket ss){                          //传递通信套接字
        this.ss=ss;
    }
    public void run(){
        MainOperation opt=new MainOperation(ss);
        opt.service();
    }

}
