package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.sql.Timestamp;


public class PostDetailPage {

    public static Client author=new Client();

    public static Post selectedPost=new Post();

    @FXML
    private TextArea commentArea;

    @FXML
    private  TextField titleField;

    @FXML
    private Button pullButton;

    @FXML
    private    Label dateField;

    @FXML
    private Button authorInfoButton;

    @FXML
    private  TextArea articleArea;

    @FXML
    private    Label nicknameField;

    @FXML
    private AnchorPane childAnchor;

    @FXML
    void authorButton(ActionEvent event) {
        if(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().equals(Login.clientLocal.getClientId())){
            //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            Hint.pop("是你自己！请自己点tab页跳转，，");
        }
        else{
            author.setClientId(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId());
            try {
                //写数据
                DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
                //读数据
                DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

                out.writeUTF("searchAuthor");                                        //向服务器说是登陆
                out.flush();

                //用户信息
                ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
                oos.writeObject(author);                                  //将用户信息发过去
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
                Object obj = ois.readObject();
                Client replyClient = (Client) obj;


                if(replyClient.equals(author)==false){
                    author=replyClient;
                    Hint.pop("查找此人成功！请自己点tab页跳转，，");
                }
                else{
                    Hint.pop("查找失败!");
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
    @FXML
    void editButton(ActionEvent event) {
        if(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).
                getClientId().equals(Login.clientLocal.getClientId())){
            //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            try {
                //写数据
                DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
                //读数据
                DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

                out.writeUTF("editPost");                                        //向服务器说是登陆
                out.flush();

                selectedPost=HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex);
                selectedPost.setPostTitle(titleField.getText());
                selectedPost.setPostArticle(articleArea.getText());
                selectedPost.setPostNewDate(Timestamp.valueOf(dateField.getText()));
                ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
                oos.writeObject(selectedPost);                                  //将用户信息发过去
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
                Object obj = ois.readObject();
                Post replyPost = (Post) obj;
                // TODO: 2021/5/11 修改应该设置一个static post吗，和首页传来的index改变了怎么办 

                if(replyPost.equals(selectedPost)){
                    selectedPost=replyPost;
                    Hint.pop("是你自己的帖子，已修改");
                }
                else{
                    Hint.pop("未知错误!");
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
        else{
            Hint.pop("不是你的帖子不能修改！");
        }
    }

    @FXML
    void refreshButton(ActionEvent event) {
        System.out.println("HomePage.clientPacket.postListSelectedIndex=" + HomePage.clientPacket.postListSelectedIndex);
        if (HomePage.clientPacket.postListSelectedIndex > -9999) {
/*            System.out.println( "???=" +childAnchor.getContentBias());
            System.out.println( "???=" +childAnchor.getAccessibleRole());
            System.out.println( "???=" +childAnchor.get());*/
            HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle();
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }

    public  void initialize(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);

        //if(HomePage.clientPacket.postListSelectedIndex<=-9999)childAnchor.setVisible(false);

        //childAnchor.getParent();

        if(HomePage.clientPacket.postListSelectedIndex>-9999){

            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }

/*    public static  void sshow(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
        if(HomePage.clientPacket.postListSelectedIndex>-9999){
            HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle();
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }*/

}
/*
package com.ljl.www.view;


import com.ljl.www.util.PostListControlPacket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PostDetailPage {

    @FXML
    private TextArea commentArea;

    @FXML
    private  TextField titleField;

    @FXML
    private Button pullButton;

    @FXML
    private   Label dateField;

    @FXML
    private Button authorInfoButton;

    @FXML
    private  TextArea articleArea;

    @FXML
    private   Label nicknameField;


    public  void initialize(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
       if(HomePage.clientPacket.postListSelectedIndex>-9999){
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }

}*/
