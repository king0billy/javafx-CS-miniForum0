package com.ljl.www.view;

import com.ljl.www.dao.PostListSql;
import com.ljl.www.po.Client;
import com.ljl.www.po.Post;
import com.ljl.www.util.PostListControlPacket;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

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

    @FXML
    private TextField limitField;

    public static PostListControlPacket clientPacket=new PostListControlPacket();


    public void eventOnButtonRefreshPost(ActionEvent event)throws Exception{
        DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
        out.writeUTF("postListControlPacket");
        out.flush();
// TODO: 2021/5/9 输入非数字会不安全 ，应该用性别的选择栏
        clientPacket.limit=Integer.parseInt(limitField.getText());
        clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
        clientPacket.operation.append("refresh");
       // clientPacket.operation=new StringBuffer("refresh");

        ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
        oos.writeObject(clientPacket);                                  //将用户信息发过去
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
        Object obj = ois.readObject();
        clientPacket = (PostListControlPacket) obj;

        Hint.pop("刷新成功");
        //PostListSql.createPaginationList();//发送一个符号过去要求刷新
    }

     class XCell extends ListCell<Post> {//static class XCell extends ListCell<Post>
        //ListCell<String>
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
                labelPostNewDate.setText("日期："+item.getPostNewDate().toString());
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
/*
        if(sexChoiceBox.getItems().size()==0) {
            sexChoiceBox.getItems().addAll("男", "女","保密","LGBTQ");
        }
        sexChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> sex = new_value.toString());
        preSexLabel.setText(Login.clientLocal.getClientSex());
        sexLabel.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());
*/

        postPagination.setPageFactory(new Callback<Integer, Node>() {
            //ObservableList<Post> postList = new PostListSql().query();
            //////ObservableList<String> tempShit=FXCollections.observableArrayList();

            @Override
            public Node call(Integer param) {
                //ObservableList<Post> postList = new PostListSql().query(param);//发送第几页过去，传回list
                try{
                    DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
                    out.writeUTF("postListControlPacket");
                    out.flush();


                    clientPacket.pageParam=param;
                    clientPacket.operation.delete(0,clientPacket.operation.length());//??这样很啰嗦？
                    clientPacket.operation.append("getPost");

                    ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
                    oos.writeObject(clientPacket);
                    oos.flush();


                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
                    Object obj = ois.readObject();
                    clientPacket = (PostListControlPacket) obj;


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
                lv.setCellFactory(params -> new XCell());

                lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                lv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    //lv.getSelectionModel().getSelectedItem();
                    clientPacket.postListSelectedIndex=lv.getSelectionModel().getSelectedIndex();

                    //new PostDetailPage().initialize();
/*                    try {
                        new Hint().sceneSwitch("PostDetailPage.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    allTabPane.getSelectionModel().select(postDetail);
/*                    try {

                        //FXMLLoader.load(getClass().getClassLoader().getResource("PostDetailPage.fxml"));
                        //FXMLLoader.load(getClass().getResource("/PostDetailPage.fxml"));

                        URL url = Paths.get("./src/com/ljl/www/view/PostDetailPage.fxml").toUri().toURL();
                        //FXMLLoader.load(url);
                        FXMLLoader fxmlLoader1=new FXMLLoader();
                        fxmlLoader1.load(url);
                        ((PostDetailPage)fxmlLoader1.getController()).sshow();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PostDetailPage.sshow();*/

/*                    postDetail.setOnSelectionChanged(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            Tab t = (Tab)event.getSource();
                            System.out.println("tab1被改变了 - " + t.getText());
                        }
                    });*/


/*                    try{
                        ColumnAndAnalogControl columnAndAnalogControl=new ColumnAndAnalogControl();
                        try {
                            columnAndAnalogControl.choiceAnalog();
                        } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }*/
                });
                return lv;
            }
        });
        //postPagination.setPageFactory(new Callback<0,postListView>);

    }

}

