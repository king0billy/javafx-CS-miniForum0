package com.ljl.www.view;

import com.ljl.www.dao.PostListSql;
import com.ljl.www.po.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class HomePage {

    @FXML
    private Button RefreshPost;

    @FXML
    private Tab favouritePage;

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
    //private Object XCell;

    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        PostListSql.createPaginationList();
    }

    static class XCell extends ListCell<String> { //ListCell<String>
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        CheckBox checkBoxThumbsUP=new CheckBox("点赞");
        CheckBox checkBoxFavorite=new CheckBox("收藏");
        Button buttonDetail = new Button("查看详细");
        Button buttonComment = new Button("评论");
        Button buttonReport = new Button("举报");

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, buttonDetail,checkBoxThumbsUP,checkBoxFavorite, buttonComment,buttonReport);
            HBox.setHgrow(pane, Priority.ALWAYS);
            //button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item);
                setGraphic(hbox);
            }
        }
    }

    //ObservableList<Post> postList = new PostListSql().query();
    public void initialize() {
        //postPagination.setPageCount(20);
        //postPagination.setPageCount(PostListSql.postCount/PostListSql.getLimit());
        postPagination.setMaxPageIndicatorCount(5);
        postPagination.setCurrentPageIndex(0);
        postPagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        postPagination.setPageFactory(new Callback<Integer, Node>() {
            //ObservableList<Post> postList = new PostListSql().query();
            ObservableList<String> tempShit=FXCollections.observableArrayList();

            @Override
            public Node call(Integer param) {
                PostListSql.setLimit(2);
                ObservableList<Post> postList = new PostListSql().query(param,PostListSql.getLimit());
                //if()刷新键被按下
                //下面这个不合理
                if(PostListSql.firstLogin++==1){
                    for(int i=0;i<postList.size();i++){
                        tempShit.add(postList.get(i).getPostTitle());
                    }
                }
                for(int i=0;i<postList.size();i++){tempShit.set(i,postList.get(i).getPostTitle());}
                ListView<String> lv = new ListView<>();
                    lv.setItems(tempShit);
                lv.setCellFactory(params -> new XCell());//what is params
                return lv;
            }
        });
        //postPagination.setPageFactory(new Callback<0,postListView>);

    }

}

