package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.sql.Timestamp;
/**
 * @className PostDetailPage
 * @description 事件详情页
 * @author  22427(king0liam)
 * @date 2021/5/12 17:18
 * @version 1.0
 * @since version-0.0
 */

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
        /**
         * @description 查看作者按钮
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:19
         */
        if(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId()
                .equals(Login.clientLocal.getClientId())){
            //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            Hint.pop("是你自己！请自己点tab页跳转，,，");
        }
        else{
            author.setClientId(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId());
            try {
                Client replyClient = (Client) Hint.send$Receive("searchAuthor",author);
                if(replyClient.equals(author)==false){
                    author=replyClient;
                    Hint.pop("查找此人成功！请自己点tab页跳转，，,");
                    //todo 这样是可以但是TabPane无了
                    //new Hint().sceneSwitch("AnotherClient.fxml",event);

                    Parent root= FXMLLoader.load(getClass().getResource("HomePage.fxml"));

                    TabPane shit= (TabPane) (root.lookup("#allTabPane"));
                    shit.getSelectionModel().select(9);///8或者9，10
                    root.lookup("#otherInfo").lookup("#refreshButton").fireEvent(new ActionEvent());
                }
                else{
                    Hint.pop("查找此人失败!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    void editButton(ActionEvent event) {
        /**
         * @description 修改事件按钮
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:20
         */
        if(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).
                getClientId().equals(Login.clientLocal.getClientId())){
                //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            try {
                selectedPost=HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex);
                selectedPost.setPostTitle(titleField.getText());
                selectedPost.setPostArticle(articleArea.getText());
                selectedPost.setPostNewDate(Timestamp.valueOf(dateField.getText()));
                Post replyPost = (Post) Hint.send$Receive("editPost",selectedPost);
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
        /**
         * @description 刷新键
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:22
         */
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
        /**
         * @description 没用的初始化页,原本想通过此控制器外的按键触发此动作
         * @exception
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:22
         */
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
        if(HomePage.clientPacket.postListSelectedIndex>-9999){
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }
        //没用的方法,原本想通过此控制器外的按键触发此函数
/*    public static  void sShow(){
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

