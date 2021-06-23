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

/**
 * @className MainOperation
 * @description 服务器主逻辑
 * @author  22427(king0liam)
 * @date 2021/5/12 15:15
 * @version 1.0
 * @since version-0.0
 */

public class MainOperation {
    public Socket ss;
    public static DBServer db;

    public MainOperation(Socket ss) {
        this.ss = ss;
        db = new DBServer();
    }

    public void service() {
        /**
         * @description 主服务处理登录和注册请求的函数,登录成功了就一直等待客户端发来其他请求
         * @exception IOException,ClassNotFoundException
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:17
         */
        try {
            //写入
            DataInputStream in = new DataInputStream(ss.getInputStream());
            //读出
            DataOutputStream out = new DataOutputStream(ss.getOutputStream());

            //循环查询，看是否有请求
            while (true) {
                String recv = in.readUTF();
                if (recv.equals("enroll")) {
                    System.out.println("equals=enroll");
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();

                    Client tempClient = (Client) obj;
                    System.out.println(tempClient.getClientTel());
                    Long tempId=db.insertClient(tempClient);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(tempId);
                    oos.flush();
                    //out.writeLong(tempId);

                } else if (recv.equals("enter")) {
                    System.out.println("equals=enter");
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();

                    Client tempClient = (Client) obj;
                    Client r4return =db.loginClient(tempClient);
                    //将用户信息发回去
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(r4return);
                    oos.flush();
                    if (tempClient.equals(r4return)==false) {
                        //登陆成功，用户存在
                        System.out.println("equals=enterSuccess");
                        //登陆成功之后，不停监视用户请求
                        while (true) {
                            recv = in.readUTF();
                            //调用下一个函数
                            mainService(recv);
                        }

                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mainService(String server_type) {
        /**
         * @description 登录成功以后接收的服务信息和处理逻辑
         * @exception IOException,ClassNotFoundException
         * @param [java.lang.String] [server_type]
         * @return [java.lang.String]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:19
         */
        try {
            //todo 可以利用正则表达式实现log(n)查找?
            if(server_type.equals("postListControlPacket")){

                System.out.println("postListControlPacket");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                PostListControlPacket ppcp= (PostListControlPacket) obj;

                System.out.println("ppcp="+ppcp.operation);


                if(ppcp.operation.toString().equals("refreshPostList")){
                    db.setPaginationList(ppcp);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(ppcp);
                    oos.flush();
                }
                else if(ppcp.operation.toString().equals("getPost")){
                    System.out.println("equals=getPost");
                    db.getPostListView(ppcp);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(ppcp);
                    oos.flush();

                }
                else if(ppcp.operation.toString().equals("getPulledPostList")){
                    System.out.println("equals=getPulledPostList");
                    db.getPulledList(ppcp);
                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(ppcp);
                    oos.flush();

                }
            }
            else if(server_type.equals("editMyInfo")){
                    System.out.println("editMyInfo");
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                    Object obj = ois.readObject();
                    Client client = (Client) obj;

                    ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                    oos.writeObject(db.updateMyInfo(client));
                    oos.flush();

            }
            else if(server_type.equals("searchAuthor")){
                System.out.println("searchAuthor");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                Client client = (Client) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.authorQuery(client));
                oos.flush();

            }
            else if(server_type.equals("editPost")){
                System.out.println("editPost");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                Post post = (Post) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.updateMyPostInfo(post));
                oos.flush();

            }
            else if(server_type.equals("deletePost")){
                System.out.println("deletePost");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                Post post = (Post) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.deletePost(post));
                oos.flush();
            }
            else if(server_type.equals("pullNewPost")){
                System.out.println("pullNewPost");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                Post post = (Post) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.newPost(post));
                oos.flush();
            }
            else if(server_type.equals("showThumbsUp")){
                System.out.println("showThumbsUp");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                ThumbsUp thumbsUp = (ThumbsUp) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.showThumbsUp(thumbsUp));
                oos.flush();
            }
            else if(server_type.equals("addThumbsUp")){
                System.out.println("addThumbsUp");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                ThumbsUp thumbsUp = (ThumbsUp) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.addThumbsUp(thumbsUp));
                oos.flush();
            }
            else if(server_type.equals("deleteThumbsUp")){
                System.out.println("deleteThumbsUp");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ss.getInputStream()));
                Object obj = ois.readObject();
                ThumbsUp thumbsUp = (ThumbsUp) obj;

                ObjectOutputStream oos=new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(db.deleteThumbsUp(thumbsUp));
                oos.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

