package com.ljl.www.service.mainlogic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.ljl.www.po.*;
import com.ljl.www.dao.*;



public class MainOperation {
    public Socket ss;
    public static DriverUtils db;

    public MainOperation(Socket ss) {
        this.ss = ss;
        db = new DBServer();
    }

    public void service() {                                                           //主服务处理函数
        try {
            //写入
            DataInputStream in = new DataInputStream(ss.getInputStream());
            //读出
            DataOutputStream out = new DataOutputStream(ss.getOutputStream());

            //循环查询，看是否有请求
            while (true) {
                String recv = in.readUTF();
                if (recv.equals("zhuce")) {
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();

                    Client pp = (Client) obj;
                    System.out.println(pp.getName());
                    if (db.addUser(pp)) {                                            //如果添加用户成功
                        out.write(1);
                        out.flush();
                    } else {                                                          //如果失败
                        out.write(2);
                        out.flush();
                    }
                } else if (recv.equals("denglu")) {
                    String msg = in.readUTF();                                       //读出信息

                    String[] items = msg.split("\\+");

                    String name = items[0];
                    String password = items[1];

                    if (db.findUser(name, password)) {
                        out.write(1);                                              //登陆成功，用户存在
                        out.flush();

                        while (true) {                                               //登陆成功之后，不停监视用户请求
                            recv = in.readUTF();
                            mainService(recv);
                        }

                    } else {
                        out.write(2);                                              //登陆失败，密码错误或用户不存在
                        out.flush();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void mainService(String server_type) {
        try {
            //写入
            DataInputStream in = new DataInputStream(ss.getInputStream());
            //读出
            DataOutputStream out = new DataOutputStream(ss.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

