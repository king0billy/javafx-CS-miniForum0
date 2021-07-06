package com.ljl.www.view;

import com.ljl.www.po.Post;
import com.ljl.www.po.Remark;
import com.ljl.www.util.PostListControlPacket;
import com.sun.glass.ui.Screen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

import java.io.*;
/**
 * @className HomePage
 * @description 为实现分页listView而写的非常复杂的控制类
 * @author  22427(king0liam)
 * @date 2021/6/18 15:39
 * @version 1.0
 * @since version-0.0
 */
public class HomePage {
    @FXML private PostDetailPage PostDetailPage;

    @FXML
    private TabPane allTabPane;

    @FXML
    private Button refreshPost;
    @FXML
    private ChoiceBox numberChoice;
/*    @FXML
    private Button HomePageButton;
    @FXML
    private Button BackButton;*/
    @FXML
    private Pagination postPagination;

    @FXML
    private Tab favouritePage;

    @FXML
    private Tab postDetail;

    @FXML
    private Tab personalInfo;

    @FXML
    private Tab otherInfo;

    @FXML
    private Tab pullPostTab;

    @FXML
    private Tab pulledPostTab;

    @FXML
    private Tab pulledCommentTab;

    @FXML
    private Tab thumbsUpPage;

    @FXML
    private Tab homePage;

    @FXML
    private Tab reportPage;

    @FXML
    private TextField limitField;

    @FXML
    private ListView<Post> topListView;

    public static PostListControlPacket clientPacket=new PostListControlPacket();
    public static PostListControlPacket topList=new PostListControlPacket();

    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        /**
         * @description 按下刷新键的事件函数
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 15:43
         */
        //clientPacket.limit=Integer.parseInt(numberChoice.get());
        clientPacket.operation.delete(0,clientPacket.operation.length());
        clientPacket.operation.append("refreshPostList");
        clientPacket = (PostListControlPacket)Hint.send$Receive("postListControlPacket",clientPacket);
        initialize();
        Hint.pop("刷新成功");
    }

    class XCell extends ListCell<Post> {
        /**
         * @className XCell
         * @description 内部类,为了实现listView的pageFactory而设
         * @author  22427(king0liam)
         * @date 2021/6/18 15:45
         * @version 1.0
         * @since version-0.0
         */
        HBox hbox = new HBox();
        Label labelTitle = new Label("");
        Label labelClientId = new Label("nothing");
        Label labelPostNewDate = new Label("nothing");
        Label labelArticle = new Label("nothing");
        Label labelRemarkCount = new Label("nothing");
        Label labelThumbsUpCount = new Label("nothing");
        Label labelVisible = new Label("nothing");
        Pane pane = new Pane();
        //弃用,必须选中listView中的一条才能得到事件的信息,这些按键就啰嗦无用了
        /*
        CheckBox checkBoxThumbsUP=new CheckBox("点赞");
        CheckBox checkBoxFavorite=new CheckBox("收藏");
        Button buttonDetail = new Button("查看详细");
        Button buttonComment = new Button("评论");
        Button buttonReport = new Button("举报");
*/
        public XCell() {
            /**
             * @description 设置listView每条记录的元素
             * @exception
             * @param [] []
             * @return []
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/6/18 15:54
             */
            super();
            hbox.setSpacing(10);
            hbox.setMargin(labelTitle, new Insets(0, 10, 0, 0));
            hbox.setMargin(labelArticle, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelClientId, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelPostNewDate, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelThumbsUpCount, new Insets(0, 10, 0, 10));
            hbox.setMargin(labelRemarkCount, new Insets(0, 10, 0, 10));
            //hbox.setMargin(labelVisible, new Insets(0, 10, 0, 10));
            hbox.getChildren().addAll(labelTitle,labelArticle,labelClientId,labelPostNewDate,
                    labelThumbsUpCount,labelRemarkCount,labelVisible
                    ,pane
/*                    , buttonDetail,checkBoxThumbsUP,checkBoxFavorite,buttonComment,buttonReport*/
            );
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        protected void updateItem(Post item, boolean empty) {
            /**
             * @description xCell类会自动调用这个更新
             * @exception
             * @param [com.ljl.www.po.Post, boolean] [item, empty]
             * @return [com.ljl.www.po.Post, boolean]
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/6/18 16:03
             */
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                labelTitle.setText("标题："+item.getPostTitle());
                if(item.getPostArticle().length()<=20){labelArticle.setText("文章摘要："+item.getPostArticle());}
                else{labelArticle.setText("摘要："+item.getPostArticle().substring(0,20));}
                labelClientId.setText("作者id："+item.getClientId().toString());
                labelPostNewDate.setText("创建or最后修改日期："+item.getPostNewDate().toString());
                labelThumbsUpCount.setText("点赞数: "+item.getThumbsUpCount().toString());
                labelRemarkCount.setText("评论数: "+item.getRemarkCount().toString());
                labelVisible.setText("优先级: "+item.getVisible().toString());
                setGraphic(hbox);
            }
        }
    }

    public void initialize() throws IOException, ClassNotFoundException {
        /**
         * @description 首页的初始化函数
         * @exception
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 16:08
         */
        topList.clientId=Login.clientLocal.getClientId();
        topList.operation.delete(0,topList.operation.length());//??这样很啰嗦？
        topList.operation.append("getTopList");
        topList = (PostListControlPacket) Hint.send$Receive("postListControlPacket",topList);

        topListView.setItems(FXCollections.observableList(topList.postList));
        //根据xCell setCellFactory
        topListView.setCellFactory(params -> new XCell());
        topListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        topListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //topListView.getSelectionModel().getSelectedItem();
            topList.postListSelectedIndex=topListView.getSelectionModel().getSelectedIndex();

            allTabPane.getSelectionModel().select(1);
            PostDetailPage.selectedPost=topList.postList.get(topList.postListSelectedIndex);
            postDetail.getContent().lookup("#refreshPostDetail").fireEvent(new ActionEvent());
        });

        postPagination.setMaxPageIndicatorCount(10);
        postPagination.setCurrentPageIndex(0);
        postPagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        if(numberChoice.getItems().size()==0) {//limit=0有bug
            numberChoice.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11");
        }
        numberChoice.getSelectionModel().selectedIndexProperty().addListener(//我怀疑从0开始是索引!
                (ov, value, new_value) -> clientPacket.limit = Integer.parseInt(new_value.toString())+1
        );
        
        postPagination.setPageFactory(new Callback<Integer, Node>() {
            /**
             * @className Callback<Integer, Node>
             * @description 匿名对象
             * @author  22427(king0liam)
             * @date 2021/6/18 16:44
             * @version 1.0
             * @since version-0.0
             */
            @Override
            public Node call(Integer param) {
                /**
                 * @description 重写匿名对象的方法,每次切换分页(页码改变)的时候被调用
                 * @exception Exception
                 * @param [java.lang.Integer] [param]
                 * @return [java.lang.Integer]
                 * @since version-1.0
                 * @author 22427(king0liam)
                 * @date 2021/6/18 16:42
                 */
                try{
                    clientPacket.pageParam=param;
                    clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
                    clientPacket.operation.append("getPost");
                    clientPacket = (PostListControlPacket)Hint.send$Receive("postListControlPacket",clientPacket);

                    //System.out.println("HomePage.clientPacket.firstLogin= "+HomePage.clientPacket.firstLogin);
                    if(HomePage.clientPacket.firstLogin==1){
                        //耦合程度比较高
                        pulledPostTab.getContent().lookup("#refreshList").fireEvent(new ActionEvent());
                        HomePage.clientPacket.firstLogin++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                ListView<Post> lv = new ListView<>();
                lv.setItems(FXCollections.observableList(clientPacket.postList));
                lv.setCellFactory(params -> new XCell());
                lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                //添加监视器
                lv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    clientPacket.postListSelectedIndex=lv.getSelectionModel().getSelectedIndex();
                    allTabPane.getSelectionModel().select(1);
                    //allTabPane.getSelectionModel().select(postDetail);
                    PostDetailPage.selectedPost=HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex);
                    postDetail.getContent().lookup("#refreshPostDetail").fireEvent(new ActionEvent());
                });
                return lv;
            }
        });
    }

}

