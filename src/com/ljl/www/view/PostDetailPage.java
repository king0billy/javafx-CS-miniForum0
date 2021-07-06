package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import com.ljl.www.po.Remark;
import com.ljl.www.po.ThumbsUp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @className PostDetailPage
 * @description 事件详情页
 * @author  22427(king0liam)
 * @date 2021/6/18 17:18
 * @version 1.0
 * @since version-0.0
 */

public class PostDetailPage {
    public static ArrayList<Remark> remarkList=new ArrayList(){{
        add(new Remark());
    }};
    public static Remark newRemark=new Remark();

    public static Client author=new Client();

    public static Post selectedPost=new Post();

    public static ThumbsUp thumbsUpSelected=new ThumbsUp();

    private ChangeListener changeListener=new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            // TODO sql
            ThumbsUp returnThumbsUp;
            if (newValue) {//0 to 1 发生点赞
                thumbsUpSelected.setThumbsUpNewDate(new Timestamp(System.currentTimeMillis()));
                try {
                    returnThumbsUp = (ThumbsUp) Hint.send$Receive("addThumbsUp", thumbsUpSelected);
                    if (thumbsUpSelected.equals(returnThumbsUp)) {
                        Hint.pop("点赞失败");
                    } else {
                        thumbsUpSelected = returnThumbsUp;
                        selectedPost.setThumbsUpCount(selectedPost.getThumbsUpCount()+1);
                        refreshPostDetail.fireEvent(new ActionEvent());
                        Hint.pop("点赞成功");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                thumbsUpSelected.setThumbsUpDropDate(new Timestamp(System.currentTimeMillis()));
                try {
                    returnThumbsUp = (ThumbsUp) Hint.send$Receive("deleteThumbsUp", thumbsUpSelected);
                    if (thumbsUpSelected.equals(returnThumbsUp)==false) {
                        selectedPost.setThumbsUpCount(selectedPost.getThumbsUpCount()-1);
                        refreshPostDetail.fireEvent(new ActionEvent());
                        Hint.pop("取消点赞成功");
                    }
                    else{
                        Hint.pop("取消点赞失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //public static PostListControlPacket clientPacket=new PostListControlPacket();
    @FXML
    private Label thumbsUpCountLabel;
    @FXML
    private CheckBox FavouriteCheckBox;

    @FXML
    private TextField titleField;

    @FXML
    private Button deleteCurrentPostButton;

    @FXML
    private Label dateField;

    @FXML
    private ListView<Remark> commentListView;

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
    private HTMLEditor articleArea;

    @FXML
    private Label nicknameField;
    @FXML
    private Button insertRemarkButton;
    @FXML
    private TextField levelField;
    @FXML
    private Button updateLevelButton;

    @FXML
    void authorButton(ActionEvent event) {
        /**
         * @description 查看作者按钮
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 17:19
         */
        if(selectedPost.getClientId()
                .equals(Login.clientLocal.getClientId())){
            //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
            Hint.pop("是你自己！");
            Parent root= childAnchor.getParent().getParent();
            TabPane tabPane=(TabPane) root;
            //tabPane.getSelectionModel().select(8);
            tabPane.getSelectionModel().select(4);
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
                    //tabPane.getSelectionModel().select(9);
                    tabPane.getSelectionModel().select(5);
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
         * @date 2021/6/18 17:20
         */
                //childAnchor.getParent().allTabPane.getSelectionModel().select(postDetail);
        if(selectedPost.getClientId().equals(Login.clientLocal.getClientId())){
            if(Login.clientLocal.getClientPrivilege()<=2){
                Hint.pop("你仅能浏览!");return;
            }
            try {
                if(titleField.getText().length()>0)selectedPost.setPostTitle(titleField.getText());
                else{
                    selectedPost.setPostTitle(articleArea.getHtmlText().substring(0,10));
                }
                selectedPost.setPostArticle(articleArea.getHtmlText());
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
    void refreshButton(ActionEvent event) throws IOException, ClassNotFoundException {
        /**
         * @description 刷新键
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 17:22
         */
        initialize();
    }
    @FXML
    void deleteButton(ActionEvent event) throws IOException, ClassNotFoundException {
        /**
         * @description 删除键
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/12 17:22
         */
        if(selectedPost.getClientId().equals(Login.clientLocal.getClientId())){
            if(Login.clientLocal.getClientPrivilege()<=2){
                Hint.pop("你仅能浏览!");return;
            }
            try {
                Post replyPost = (Post) Hint.send$Receive("deletePost",selectedPost);
                if(replyPost.equals(selectedPost)){
                    selectedPost=replyPost;
                    Hint.pop("是你自己的帖子，已删除");
                }
                else{
                    Hint.pop("未知错误!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            if(Login.clientLocal.getClientPrivilege()>4){
                try {
                    Post replyPost = (Post) Hint.send$Receive("deletePost",selectedPost);
                    if(replyPost.equals(selectedPost)){
                        selectedPost=replyPost;
                        Hint.pop("不是你的帖子,但你是管理员,已删除");
                    }
                    else{
                        Hint.pop("未知错误!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Hint.pop("不是你的帖子且你不是管理员不能修改！");
            }
        }
    }
    @FXML
    void eventOnInsertRemarkButton(ActionEvent event) throws IOException, ClassNotFoundException {//回复选中评论
        if(Login.clientLocal.getClientPrivilege()<=2){
            Hint.pop("你仅能浏览!");return;
        }
        newRemark.setClientId(Login.clientLocal.getClientId());
        newRemark.setFatherId(selectedPost.getPostId());
        newRemark.setRemarkArticle(commentArea.getText());
        if(newRemark.getRemarkArticle().length()<5){
            Hint.pop("评论字数不能少于5个字!");return;
        }
        newRemark.setToFloor(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getFloor());
        Remark returnRemark=(Remark) Hint.send$Receive("insertRemark",newRemark);
        if(returnRemark.equals(newRemark)==false){
            refreshButton(new ActionEvent());
            Hint.pop("插入成功");
        }
        else{
            Hint.pop("插入失败");
        }

    }
    @FXML
    void eventOnInsertRemarkToPostButton(ActionEvent event) throws IOException, ClassNotFoundException {//回复贴子
        if(Login.clientLocal.getClientPrivilege()<=2){
            Hint.pop("你仅能浏览!");return;
        }
        newRemark.setClientId(Login.clientLocal.getClientId());
        newRemark.setFatherId(selectedPost.getPostId());
        newRemark.setRemarkArticle(commentArea.getText());
        if(newRemark.getRemarkArticle().length()<5){
            Hint.pop("评论字数不能少于5个字!");return;
        }
        newRemark.setToFloor(0);
        Remark returnRemark=(Remark) Hint.send$Receive("insertRemark",newRemark);
        if(returnRemark.equals(newRemark)==false){
            refreshButton(new ActionEvent());
            Hint.pop("插入成功");
        }
        else{
            Hint.pop("插入失败");
        }
    }
    @FXML
    void eventOnEditRemarkButton(ActionEvent event) throws IOException, ClassNotFoundException {
        if(Login.clientLocal.getClientPrivilege()<=2){
            Hint.pop("你仅能浏览!");return;
        }
        newRemark.setClientId(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getClientId());
        newRemark.setFatherId(selectedPost.getPostId());
        newRemark.setFloor(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getFloor());
        newRemark.setRemarkArticle(commentArea.getText());
        if( !Login.clientLocal.getClientId().equals(newRemark.getClientId())){
            Hint.pop("不是你的评论不能编辑");
        }
        else{
            Remark returnRemark=(Remark) Hint.send$Receive("editRemark",newRemark);
            if(returnRemark.equals(newRemark)==false){
                refreshButton(new ActionEvent());
                Hint.pop("编辑成功");
            }
            else{
                Hint.pop("编辑失败");
            }
        }
    }
    @FXML
    void eventOnDeleteRemarkButton(ActionEvent event) throws IOException, ClassNotFoundException {
        if(Login.clientLocal.getClientPrivilege()<=2){
            Hint.pop("你仅能浏览!");return;
        }
        newRemark.setClientId(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getClientId());
        newRemark.setFatherId(selectedPost.getPostId());
        newRemark.setFloor(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getFloor());

        if( !Login.clientLocal.getClientId().equals(newRemark.getClientId())&& Login.clientLocal.getClientPrivilege()>4&& !Login.clientLocal.getClientId().equals(selectedPost.getClientId())){
            Hint.pop("不是你的评论,且你不是管理员，且你不是楼主,不能删除");
        }
        else{
            Remark returnRemark=(Remark) Hint.send$Receive("deleteRemark",newRemark);
            if(returnRemark.equals(newRemark)==false){
                refreshButton(new ActionEvent());
                Hint.pop("删除成功");
            }
            else{
                Hint.pop("删除失败");
            }
        }
    }
    @FXML
    void eventOnuUpdateLevelButton(ActionEvent event) {
        if(Login.clientLocal.getClientPrivilege()<=4){
            Hint.pop("权限不够!");
        }
        else{
            try {
                selectedPost.setVisible(Byte.parseByte(levelField.getText()));
                Post replyPost = (Post) Hint.send$Receive("updatePostLevel",selectedPost);
                if(replyPost.equals(selectedPost)){
                    selectedPost=replyPost;
                    Hint.pop("已设置好指定等级");
                }
                else{
                    Hint.pop("未知错误!");
                }
            } catch (Exception e) {
                Hint.pop("输入范围出错或格式不对!");
                e.printStackTrace();
            }
        }
    }
    public  void initialize() throws IOException, ClassNotFoundException {
        /**
         * @description 没用的初始化页,原本想通过此控制器外的按键触发此动作
         * @exception
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 17:22
         */
        if(HomePage.clientPacket.firstLogin>=2){
            thumbsUpCheckBox.selectedProperty().removeListener(changeListener);

            thumbsUpSelected.setClientId(Login.clientLocal.getClientId());
            thumbsUpSelected.setPostId(selectedPost.getPostId());

            ThumbsUp returnThumbsUp=thumbsUpSelected;
            returnThumbsUp= (ThumbsUp) Hint.send$Receive("showThumbsUp",thumbsUpSelected);

            if(thumbsUpSelected.equals(returnThumbsUp)){
                thumbsUpCheckBox.setSelected(true);
            }
            else {
                thumbsUpCheckBox.setSelected(false);
            }

            thumbsUpCheckBox.selectedProperty().addListener(changeListener);
            thumbsUpCountLabel.setText(selectedPost.getThumbsUpCount().toString());

            titleField.setText(selectedPost.getPostTitle());
            articleArea.setHtmlText(selectedPost.getPostArticle());//articleArea.getHtmlText(selectedPost.getPostArticle());
            dateField.setText(selectedPost.getPostNewDate().toString());
            nicknameField.setText(selectedPost.getClientId().toString());
            levelField.setText(selectedPost.getVisible().toString());
        }

//
            /**
             * @description 按下刷新键再查评论表(楼数如何控制)
             * @exception IOException, ClassNotFoundException
             * @param [] []
             * @return []
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/6/18 17:30
             */

            if(HomePage.clientPacket.firstLogin>=2){

                commentListView.setItems(FXCollections.observableList(new ArrayList<>()));//为了评论区清空

                //remarkList=new ArrayList<>();
//                remarkList=new ArrayList(){{
//                    add(new Remark());
//                }};

                remarkList.get(0).setFatherId(selectedPost.getPostId());

                remarkList.get(0).setClientId(-9866564);//纯粹是为了使得1->2->1显示平论区正确

                ArrayList<Remark> replyRemark=remarkList;
                Object obj=Hint.send$Receive("getRemarkList",remarkList);
                if (obj instanceof ArrayList<?>){
                    replyRemark = (ArrayList<Remark>) obj;
                }
                //commentListView.setCellFactory(
                if (remarkList.equals(replyRemark)==false){
                    remarkList=replyRemark;

                    commentListView.setItems(FXCollections.observableList(remarkList));
                    //根据xCell setCellFactory
                    commentListView.setCellFactory(params -> new PostDetailPage.XCell());
                    commentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    commentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        if(remarkList.size()>=1){//防空指针
                            commentArea.setText(remarkList.get(commentListView.getSelectionModel().getSelectedIndex()).getRemarkArticle());
                        }
                });
                }
            }
    }

    class XCell extends ListCell<Remark> {
        /**
         * @className XCell
         * @description 内部类
         * @author  22427(king0liam)
         * @date 2021/6/18 17:36
         * @version 1.0
         * @since version-0.0
         */
        HBox hbox = new HBox();
        Label labelFloor = new Label("");
        Label label2Floor = new Label("");
        Label labelClientId = new Label("nothing");
        Label labelRemarkNewDate = new Label("nothing");
        Label labelArticle = new Label("nothing");
        Pane pane = new Pane();

        public XCell() {
            /**
             * @description 默认构造方法
             * @exception
             * @param [] []
             * @return []
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/6/18 17:40
             */
            super();
            hbox.setSpacing(10);
            hbox.setMargin(labelFloor, new Insets(0, 10, 0, 0));
            hbox.setMargin(label2Floor, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelClientId, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelArticle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelRemarkNewDate, new Insets(0, 10, 0, 10));
            hbox.getChildren().addAll(labelFloor,labelClientId,label2Floor,labelArticle,labelRemarkNewDate
                    ,pane
            );
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        protected void updateItem(Remark item, boolean empty) {
            /**
             * @description 自动调用的更新方法
             * @exception
             * @param [com.ljl.www.po.Post, boolean] [item, empty]
             * @return [com.ljl.www.po.Post, boolean]
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/6/18 17:44
             */
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                labelFloor.setText("楼层:"+item.getFloor());
                labelClientId.setText("作者id："+item.getClientId());
                if(item.getRemarkArticle().length()<=10){labelArticle.setText("文章摘要："+item.getRemarkArticle());}
                else{labelArticle.setText("文章摘要："+item.getRemarkArticle().substring(0,10));}
                label2Floor.setText("回复楼层:"+item.getToFloor());
                labelRemarkNewDate.setText("创建or最后修改日期："+item.getRemarkNewDate().toString());
                setGraphic(hbox);
            }
        }
    }//

}

