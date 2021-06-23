package com.ljl.www.view;

import com.ljl.www.po.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.sql.Timestamp;

/**
 * @className NewPost
 * @description 发布新事件页面
 * @author  22427(king0liam)
 * @date 2021/5/12 17:16
 * @version 1.0
 * @since version-0.0
 */
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
        /**
         * @description 发布新事件按钮
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:17
         */
        try {
            if(articleArea.getText().length()<=10){
                Hint.pop("贴子不能少于10个字!");return;
            }
            postToPull.setClientId(Long.parseLong(nicknameField.getText()));
            postToPull.setPostTitle(titleField.getText());
            postToPull.setPostArticle(articleArea.getText());
            //postToPull.setPostNewDate(new Timestamp(System.currentTimeMillis()));

            Post replyPost = (Post) Hint.send$Receive("pullNewPost",postToPull);

            if(replyPost.equals(postToPull)){
                HomePage.clientPacket.firstLogin=0;
                Hint.pop("请回首页刷新查看或是去已发布栏查看");
                System.out.println("请回首页刷新查看或是去已发布栏查看");//用手机号注册或者，用文本框显示注册的账号，
            }
            else{
                Hint.pop("新建事件失败,数据库错误");
                System.out.println("新建事件失败,数据库错误");
            }
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }

    }
    public  void initialize(){
        /**
         * @description 页面初始化
         * @exception
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:17
         */
        dateField.setText(new Timestamp(System.currentTimeMillis()).toString());
        nicknameField.setText(Login.clientLocal.getClientId().toString());

    }

}