package com.ljl.www.view;

import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import com.sun.glass.ui.Screen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
 * @date 2021/5/12 15:39
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
    private Tab pullPostTab;

    @FXML
    private Tab pulledCommentTab;

    @FXML
    private Tab thumbsUpPage;

    @FXML
    private Button BackButton;

    @FXML
    private Pagination postPagination;


    @FXML
    private Tab homePage;

    @FXML
    private Tab reportPage;

    @FXML
    private TextField limitField;

    public static PostListControlPacket clientPacket=new PostListControlPacket();


    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        /**
         * @description 按下刷新键的事件函数
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:43
         */
        clientPacket.limit=Integer.parseInt(limitField.getText());
        //??这样很啰嗦？clientPacket.operation=new StringBuffer("refresh");
        clientPacket.operation.delete(0,clientPacket.operation.length());
        clientPacket.operation.append("refreshPostList");
        clientPacket = (PostListControlPacket)Hint.send$Receive("postListControlPacket",clientPacket);
        Hint.pop("刷新成功");
    }

    class XCell extends ListCell<Post> {
        /**
         * @className XCell
         * @description 内部类,为了实现listView的pageFactory而设
         * @author  22427(king0liam)
         * @date 2021/5/12 15:45
         * @version 1.0
         * @since version-0.0
         */
        HBox hbox = new HBox();
        Label labelTitle = new Label("");
        Label labelClientId = new Label("shit");
        Label labelPostNewDate = new Label("shit");
        Label labelArticle = new Label("shit");
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
             * @date 2021/5/12 15:54
             */
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

        protected void updateItem(Post item, boolean empty) {
            /**
             * @description xCell类会自动调用这个更新
             * @exception
             * @param [com.ljl.www.po.Post, boolean] [item, empty]
             * @return [com.ljl.www.po.Post, boolean]
             * @since version-1.0
             * @author 22427(king0liam)
             * @date 2021/5/12 16:03
             */
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                labelTitle.setText("标题："+item.getPostTitle());
                if(item.getPostTitle().length()<=20){labelArticle.setText("文章摘要："+item.getPostArticle());}
                else{labelArticle.setText("文章摘要："+item.getPostArticle().substring(0,20));}
                labelClientId.setText("作者id："+item.getClientId().toString());
                labelPostNewDate.setText("创建or最后修改日期："+item.getPostNewDate().toString());
                setGraphic(hbox);
            }
        }
    }

    public void initialize() {
        /**
         * @description 首页的初始化函数
         * @exception
         * @param [] []
         * @return []
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 16:08
         */

        //postPagination.setPageCount(PostListSql.postCount/PostListSql.getLimit());
        postPagination.setMaxPageIndicatorCount(10);
        postPagination.setCurrentPageIndex(0);
        postPagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);


/* // TODO: 2021/5/9 输入非数字会不安全 ，应该用类似性别的choiceBox
// TODO: 2021/5/12 没实现的每页选择栏
        if(sexChoiceBox.getItems().size()==0) {
            sexChoiceBox.getItems().addAll("1", "2","3","4","5","6","7","8","9","10","11");
        }
        sexChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> sex = new_value.toString());
        preSexLabel.setText(Login.clientLocal.getClientSex());
        sexLabel.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());
*/

        postPagination.setPageFactory(new Callback<Integer, Node>() {
            /**
             * @className Callback<Integer, Node>
             * @description 匿名对象
             * @author  22427(king0liam)
             * @date 2021/5/12 16:44
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
                 * @date 2021/5/12 16:42
                 */
                try{
                    clientPacket.pageParam=param;
                    clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
                    clientPacket.operation.append("getPost");
                    clientPacket = (PostListControlPacket)Hint.send$Receive("postListControlPacket",clientPacket);
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
                    postDetail.getContent().lookup("#refreshPostDetail").fireEvent(new ActionEvent());
                });
                return lv;
            }
        });
    }

}

