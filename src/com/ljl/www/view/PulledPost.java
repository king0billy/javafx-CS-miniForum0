package com.ljl.www.view;

import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.*;
import java.util.ArrayList;

public class PulledPost {

    @FXML
    private Button refreshList;

/*    @FXML
    private ListView<?> pulledListView;*/
    @FXML
    private ListView<Post> pulledListView;

    //public  static ArrayList<Post> pulledPostList=new ArrayList<>();
    public  static PostListControlPacket pulledPostList=new PostListControlPacket();

    @FXML
    public  void initialize() throws IOException, ClassNotFoundException {
        DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
        out.writeUTF("postListControlPacket");
        out.flush();
        if(HomePage.clientPacket.firstLogin>0){
            pulledPostList.clientId=Login.clientLocal.getClientId();
            pulledPostList.operation.delete(0,pulledPostList.operation.length());//??这样很啰嗦？
            pulledPostList.operation.append("getPulledPostList");

            ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
            oos.writeObject(pulledPostList);                                  //将用户信息发过去
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
            Object obj = ois.readObject();
            pulledPostList = (PostListControlPacket) obj;
            pulledListView.setItems(FXCollections.observableList(pulledPostList.postList));
            //pulledListView.setCellFactory(

// TODO: 2021/5/11

            pulledListView.setItems(FXCollections.observableList(pulledPostList.postList));
            pulledListView.setCellFactory(params -> new PulledPost.XCell());

            pulledListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            pulledListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                //pulledListView.getSelectionModel().getSelectedItem();
                pulledPostList.postListSelectedIndex=pulledListView.getSelectionModel().getSelectedIndex();

                /*allTabPane.getSelectionModel().select(postDetail);
                Node shit=postDetail.getContent().lookup("#refreshPostDetail");

                System.out.println(shit);
                System.out.println(shit.localToScene(shit.getLayoutBounds()).getMinX());
                System.out.println(shit.localToScreen(shit.getLayoutBounds()).getMinX());

                double sceneX=(shit.localToScene(shit.getLayoutBounds()).getMaxX()+shit.localToScene(shit.getLayoutBounds()).getMinX())/2;
                double sceneY=(shit.localToScene(shit.getLayoutBounds()).getMaxY()+shit.localToScene(shit.getLayoutBounds()).getMinY())/2;
                double screenX=(shit.localToScreen(shit.getLayoutBounds()).getMaxX()+shit.localToScreen(shit.getLayoutBounds()).getMinX())/2;
                double screenY=(shit.localToScreen(shit.getLayoutBounds()).getMaxY()+shit.localToScreen(shit.getLayoutBounds()).getMinY())/2;

                postDetail.getContent().lookup("#refreshPostDetail").fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                        sceneX, sceneY,
                        screenX, screenY,
                        MouseButton.PRIMARY, 2,
                        true, true, true, true, true, true,
                        true, true, true, true, null));*/
            });
        }
    }
    class XCell extends ListCell<Post> {//static class XCell extends ListCell<Post>
        HBox hbox = new HBox();
        Label labelTitle = new Label("");
        Label labelClientId = new Label("shit");
        Label labelPostNewDate = new Label("shit");
        Label labelArticle = new Label("shit");
        Pane pane = new Pane();
        CheckBox checkBoxThumbsUP=new CheckBox("点赞");
        CheckBox checkBoxFavorite=new CheckBox("收藏");
        Button buttonDetail = new Button("查看详细");
        Button buttonComment = new Button("评论");
        Button buttonReport = new Button("举报");

        public XCell() {
            super();
            hbox.setSpacing(10);
            hbox.setMargin(labelTitle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelArticle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelClientId, new Insets(0, 10, 0, 50));
            hbox.getChildren().addAll(labelTitle,labelArticle,labelClientId,labelPostNewDate
                    ,pane
/*                    , buttonDetail
                  ,checkBoxThumbsUP,checkBoxFavorite,
                    buttonComment,buttonReport*/
            );
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        protected void updateItem(Post item, boolean empty) {//(String item, boolean empty)
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                labelTitle.setText("标题："+item.getPostTitle());// label.setText(item);
                if(item.getPostTitle().length()<=20){labelArticle.setText("文章摘要："+item.getPostArticle());}
                else{labelArticle.setText("文章摘要："+item.getPostArticle().substring(0,20));}
                labelClientId.setText("作者id："+item.getClientId().toString());
                labelPostNewDate.setText("创建or最后修改日期："+item.getPostNewDate().toString());
                setGraphic(hbox);
            }
        }
    }
    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
            initialize();
            Hint.pop("刷新成功");
    }



}

