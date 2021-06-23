package com.ljl.www.view;

import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import java.io.*;

/**
 * @className PulledPost
 * @description 个人已发布的事件,按发布时间倒序
 * @author  22427(king0liam)
 * @date 2021/5/12 17:28
 * @version 1.0
 * @since version-0.0
 */
public class PulledPost {

    @FXML
    private Button refreshList;

/*    @FXML
    private ListView<?> pulledListView;*/
    @FXML
    private ListView<Post> pulledListView;

    public  static PostListControlPacket pulledPostList=new PostListControlPacket();

    @FXML
    public  void initialize() throws IOException, ClassNotFoundException {
        /**
         * @description 初始化方法
         * @exception IOException, ClassNotFoundException
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:30
         */
        if(HomePage.clientPacket.firstLogin>0){
            pulledPostList.clientId=Login.clientLocal.getClientId();
            pulledPostList.operation.delete(0,pulledPostList.operation.length());//??这样很啰嗦？
            pulledPostList.operation.append("getPulledPostList");
            pulledPostList = (PostListControlPacket) Hint.send$Receive("postListControlPacket",pulledPostList);
            //pulledListView.setCellFactory(

// TODO: 2021/5/11 跳转到事件详情,删除等等
            pulledListView.setItems(FXCollections.observableList(pulledPostList.postList));
            //根据xCell setCellFactory
            pulledListView.setCellFactory(params -> new PulledPost.XCell());
            pulledListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            pulledListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                //pulledListView.getSelectionModel().getSelectedItem();
                pulledPostList.postListSelectedIndex=pulledListView.getSelectionModel().getSelectedIndex();
                PostDetailPage.selectedPost=pulledPostList.postList.get(pulledPostList.postListSelectedIndex);
                Parent root1= refreshList.getParent();
                Parent root2= root1.getParent();
                Parent root3= root2.getParent();
                TabPane tabPane=(TabPane) root3;
                tabPane.getSelectionModel().select(1);

                Node test2=tabPane.lookup("#refreshPostDetail");
                test2.fireEvent(new ActionEvent());
            });
        }
    }
    class XCell extends ListCell<Post> {
        /**
         * @className XCell
         * @description 内部类
         * @author  22427(king0liam)
         * @date 2021/5/12 17:36
         * @version 1.0
         * @since version-0.0
         */
        HBox hbox = new HBox();
        Label labelTitle = new Label("");
        Label labelClientId = new Label("nothing");
        Label labelPostNewDate = new Label("nothing");
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
             * @date 2021/5/12 17:40
             */
            super();
            hbox.setSpacing(10);
            hbox.setMargin(labelTitle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelArticle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelClientId, new Insets(0, 10, 0, 50));
            hbox.getChildren().addAll(labelTitle,labelArticle,labelClientId,labelPostNewDate
                    ,pane
            );
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        protected void updateItem(Post item, boolean empty) {
            /**
             * @description 自动调用的更新方法
             * @exception
             * @param [com.ljl.www.po.Post, boolean] [item, empty]
             * @return [com.ljl.www.po.Post, boolean]
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/5/12 17:44
             */
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                labelTitle.setText("标题："+item.getPostTitle());// label.setText(item);
                if(item.getPostArticle().length()<=20){labelArticle.setText("文章摘要："+item.getPostArticle());}
                else{labelArticle.setText("文章摘要："+item.getPostArticle().substring(0,20));}
                labelClientId.setText("作者id："+item.getClientId().toString());
                labelPostNewDate.setText("创建or最后修改日期："+item.getPostNewDate().toString());
                setGraphic(hbox);
            }
        }
    }
    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        /**
         * @description 刷新键调用初始化方法
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:35
         */
            initialize();
            Hint.pop("pulledPost刷新成功");
    }



}

