package com.ljl.www.view;

import com.ljl.www.dao.PostListSql;
import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomePage {

    @FXML
     private TabPane allTabPane;

    @FXML
    private Button refreshPost;

    @FXML
    private Tab favouritePage;

    @FXML
     private Tab postDetail;

    @FXML
    private Tab personalInfo;

    @FXML
    private Tab otherInfo;

    @FXML
    private Button HomePageButton;

    @FXML
    private Tab pullPost;

    @FXML
    private Tab pulledComment;

    @FXML
    private Tab thumbsUpPage;

    @FXML
    private Button BackButton;

    @FXML
    private Pagination postPagination;

    @FXML
    private Tab pulledPost;

    @FXML
    private Tab homePage;

    @FXML
    private Tab reportPage;

    PostListControlPacket clientPacket=new PostListControlPacket();

    //private Object XCell;

        /*private class myEventHandler implements EventHandler<ActionEvent>{
                @Override
                public void handle(ActionEvent event) {

                }
            }*/

    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
        out.writeUTF("postListControlPacket");
        out.flush();

        clientPacket.limit=4;
        clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
        clientPacket.operation.append("refresh");
       // clientPacket.operation=new StringBuffer("refresh");

        ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
        oos.writeObject(clientPacket);                                  //将用户信息发过去
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
        Object obj = ois.readObject();
        clientPacket = (PostListControlPacket) obj;


        //PostListSql.createPaginationList();//发送一个符号过去要求刷新
    }

     class XCell extends ListCell<Post> {//static class XCell extends ListCell<Post>
        //ListCell<String>
        HBox hbox = new HBox();
        Label label0 = new Label("");
        Label label1 = new Label("shit");
        Label label2 = new Label("shit");
        Pane pane = new Pane();
        CheckBox checkBoxThumbsUP=new CheckBox("点赞");
        CheckBox checkBoxFavorite=new CheckBox("收藏");
        Button buttonDetail = new Button("查看详细");
        Button buttonComment = new Button("评论");
        Button buttonReport = new Button("举报");

        public XCell() {
            super();
            hbox.setSpacing(10);
            hbox.setMargin(label0, new Insets(0, 10, 0, 10));
            hbox.setMargin(label1, new Insets(0, 10, 0, 10));
            hbox.getChildren().addAll(label0,label1,label2,pane,
                    buttonDetail,checkBoxThumbsUP,checkBoxFavorite,
                    buttonComment,buttonReport);
            HBox.setHgrow(pane, Priority.ALWAYS);
            buttonDetail.setOnAction(event -> {
                //tabPane.getTabs().add(new Tab("New Tab")); // Adding new tab at the end, so behind all the other tabs
                allTabPane.getSelectionModel().select(postDetail);//allTabPane.getSelectionModel().selectLast(); // Selecting the last tab, which is the newly created one
            });
            buttonComment.setOnAction(event -> {

                allTabPane.getSelectionModel().select(postDetail);
            });
            //button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(Post item, boolean empty) {//(String item, boolean empty)
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label0.setText("标题："+item.getPostTitle());// label.setText(item);
                label1.setText("作者id："+item.getClientId().toString());
                label2.setText("日期："+item.getPostNewDate().toString());
                setGraphic(hbox);
            }
        }
    }

    //ObservableList<Post> postList = new PostListSql().query();
    public void initialize() {
        //postPagination.setPageCount(20);
        //postPagination.setPageCount(PostListSql.postCount/PostListSql.getLimit());
        postPagination.setMaxPageIndicatorCount(10);
        postPagination.setCurrentPageIndex(0);
        postPagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        postPagination.setPageFactory(new Callback<Integer, Node>() {
            //ObservableList<Post> postList = new PostListSql().query();
            //////ObservableList<String> tempShit=FXCollections.observableArrayList();

            @Override
            public Node call(Integer param) {
                ////PostListSql.setLimit(4);//每次都要setLimit不合理

                //ObservableList<Post> postList = new PostListSql().query(param);//发送第几页过去，传回list
                try{
                    DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
                    out.writeUTF("postListControlPacket");
                    out.flush();
                    System.out.println("postListControlPacket");

                    clientPacket.pageParam=param;
                    clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
                    clientPacket.operation.append("getPost");
                    System.out.println(clientPacket.operation);
                    System.out.println("clientPacket.pageParam "+clientPacket.pageParam);
                    System.out.println("clientPacket.paginationList.size "+clientPacket.paginationList.size());
                    System.out.println("clientPacket.postList.size"+clientPacket.postList.size());

                    ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
                    oos.writeObject(clientPacket);
                    oos.flush();
                    System.out.println("clientPacket.operation "+clientPacket.operation);

                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
                    Object obj = ois.readObject();
                    clientPacket = (PostListControlPacket) obj;

                    System.out.println("shit ");
                    System.out.println("clientPacket.paginationList.size "+clientPacket.paginationList.size());
                    System.out.println("clientPacket.postList.size "+clientPacket.postList.size());
                }catch (Exception e){
                    e.printStackTrace();
                }


                //if()刷新键被按下
                /*if(PostListSql.firstLogin++==1){
                    for(int i=0;i<postList.size();i++){
                        tempShit.add(postList.get(i).getPostTitle());
                    }
                }
                for(int i=0;i<postList.size();i++){tempShit.set(i,postList.get(i).getPostTitle());}
                 */

                //ListView<String> lv = new ListView<>();
                //lv.setItems(tempShit);

                ListView<Post> lv = new ListView<>();
                //lv.setItems(clientPacket.postList);
                lv.setItems(FXCollections.observableList(clientPacket.postList));
                lv.setCellFactory(params -> new XCell());//what is params
                return lv;
            }
        });
        //postPagination.setPageFactory(new Callback<0,postListView>);

    }

}

