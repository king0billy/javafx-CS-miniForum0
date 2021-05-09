package com.ljl.www.service.mainlogic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ljl.www.po.*;
import com.ljl.www.util.*;
import com.ljl.www.dao.*;
import com.ljl.www.view.MainView;
import javafx.collections.ObservableList;


public class MainOperation {
    public Socket ss;
    public static DBServer db;

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
                if (recv.equals("enroll")) {
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();

                    Client tempClient = (Client) obj;
                    System.out.println(tempClient.getClientTel());
                    Long tempId=db.insertClient(tempClient);
                    out.writeLong(tempId);

                } else if (recv.equals("enter")) {

                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();

                    Client tempClient = (Client) obj;
                    Client r4return =db.loginClient(tempClient);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(r4return);
                    oos.flush();
                    if (tempClient.equals(r4return)) {
                        //out.write(1);                                              //登陆成功，用户存在
                        //out.flush();                                                //将用户信息发过去

                        while (true) {                                               //登陆成功之后，不停监视用户请求
                            recv = in.readUTF();
                            mainService(recv);
                        }

                    } else {
                       // out.write(2);                                              //登陆失败，密码错误或用户不存在
                        //out.flush();
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

            if(server_type.equals("postListControlPacket")){
                System.out.println("postListControlPacket");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                PostListControlPacket ppcp= (PostListControlPacket) obj;
                System.out.println("ppcp");
                System.out.println("ppcp="+ppcp.operation);


                if(ppcp.operation.toString().equals("refresh")){
                    //PostListSql.createPaginationList(ppcp);
                    db.setPaginationList(ppcp);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(ppcp);                                  //将用户信息发过去
                    oos.flush();
                }
                else if(ppcp.operation.toString().equals("getPost")){
                    System.out.println("equals=getPost");
                    db.getPostListView(ppcp);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(ppcp);                                  //将用户信息发过去
                    oos.flush();
                }
            }
            else if(server_type.equals("")){
                    System.out.println("fuck u");
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

