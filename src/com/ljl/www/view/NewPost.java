package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.sql.Timestamp;

public class NewPost{
    Post postToPull=new Post();

    @FXML
    private TextField titleField;

    @FXML
    private Button pullButton;

    @FXML
    private Label dateField;

    @FXML
    private TextArea articleArea;

    @FXML
    private Label nicknameField;

    @FXML
    void pullNewPost(ActionEvent event) {
        try {

            postToPull.setClientId(Long.parseLong(nicknameField.getText()));
            postToPull.setPostTitle(titleField.getText());
            postToPull.setPostArticle(articleArea.getText());
            //postToPull.setPostNewDate(new Timestamp(System.currentTimeMillis()));
            //写数据
            DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
            //读数据
            DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

            out.writeUTF("pullNewPost");                                //给服务器端发送注册
            out.flush();

            ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
            oos.writeObject(postToPull);                                  //将用户信息发过去
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
            Object obj = ois.readObject();
            Post replyPost = (Post) obj;

            if(replyPost.equals(postToPull)){
                HomePage.clientPacket.firstLogin=0;
                Hint.pop("请回首页刷新查看或是去已发布栏查看");
                System.out.println("请回首页刷新查看或是去已发布栏查看");//用手机号注册或者，用文本框显示注册的账号，
            }
            else{
                Hint.pop("数据库错误");
                System.out.println("failed");
            }
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }

    }
    public  void initialize(){
        dateField.setText(new Timestamp(System.currentTimeMillis()).toString());
        nicknameField.setText(Login.clientLocal.getClientId().toString());

    }

}