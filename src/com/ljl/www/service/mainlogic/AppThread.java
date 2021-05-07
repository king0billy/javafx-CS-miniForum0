package com.ljl.www.service.mainlogic;

import java.net.Socket;

//import com.ljl.www.service.mainlogic.MainOperation;

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
