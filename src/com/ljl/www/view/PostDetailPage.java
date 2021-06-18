package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private CheckBox FavouriteCheckBox;

    @FXML
    private TextField titleField;

    @FXML
    private Button deleteCurrentPostButton;

    @FXML
    private Label dateField;

    @FXML
    private ListView<?> commentListView;

    @FXML
    private CheckBox thumbsUpCheckBox;

    @FXML
    private TextArea commentArea;

    @FXML
    private Button pullButton;

    @FXML
    private Button authorInfoButton;

    @FXML
    private Button refreshPostDetail;

    @FXML
    private AnchorPane childAnchor;

    @FXML
    private TextArea articleArea;

    @FXML
    private Label nicknameField;

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
        if(selectedPost.getClientId()
                .equals(Login.clientLocal.getClientId())){
            //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            Hint.pop("是你自己！");
            Parent root= childAnchor.getParent().getParent();
            TabPane tabPane=(TabPane) root;
            tabPane.getSelectionModel().select(8);

            Node test1=tabPane.lookup("#fx_user_pane");
            Node test2=test1.lookup("#refreshButton");
            test2.fireEvent(new ActionEvent());
        }
        else{
            author.setClientId(selectedPost.getClientId());
            try {
                Client replyClient = (Client) Hint.send$Receive("searchAuthor",author);
                if(replyClient.equals(author)==false){
                    author=replyClient;
                    //Hint.pop("查找此人成功！请自己点tab页跳转，，,");

                    Parent root= childAnchor.getParent();
                    Parent root2= root.getParent();
                    TabPane tabPane=(TabPane) root2;
                    tabPane.getSelectionModel().select(9);

                    //Node test1=tabPane.lookup("#otherInfo");
                    Node test1=tabPane.lookup("#anotherClient");
                    Node test2=test1.lookup("#refreshButton");
                            test2.fireEvent(new ActionEvent());

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
                //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
        if(selectedPost.getClientId().equals(Login.clientLocal.getClientId())){
            try {
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
        titleField.setText(selectedPost.getPostTitle());
        articleArea.setText(selectedPost.getPostArticle());
        dateField.setText(selectedPost.getPostNewDate().toString());
        nicknameField.setText(selectedPost.getClientId().toString());
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
    }

}

