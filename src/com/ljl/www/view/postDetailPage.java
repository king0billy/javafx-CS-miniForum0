package com.ljl.www.view;


import com.ljl.www.util.PostListControlPacket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PostDetailPage {


    @FXML
    private TextArea commentArea;

    @FXML
    private static TextField titleField;

    @FXML
    private Button pullButton;

    @FXML
    private static   Label dateField;

    @FXML
    private Button authorInfoButton;

    @FXML
    private static TextArea articleArea;

    @FXML
    private static   Label nicknameField;


    public static void initialize(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
        if(HomePage.clientPacket.postListSelectedIndex>-9999){
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }
    public static void sshow(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
        if(HomePage.clientPacket.postListSelectedIndex>-9999){
            HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle();
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }

}
/*
package com.ljl.www.view;


import com.ljl.www.util.PostListControlPacket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PostDetailPage {

    @FXML
    private TextArea commentArea;

    @FXML
    private  TextField titleField;

    @FXML
    private Button pullButton;

    @FXML
    private   Label dateField;

    @FXML
    private Button authorInfoButton;

    @FXML
    private  TextArea articleArea;

    @FXML
    private   Label nicknameField;


    public  void initialize(){
        System.out.println("HomePage.clientPacket.postListSelectedIndex="+HomePage.clientPacket.postListSelectedIndex);
       if(HomePage.clientPacket.postListSelectedIndex>-9999){
            titleField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostTitle());
            articleArea.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostArticle());
            dateField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getPostNewDate().toString());
            nicknameField.setText(HomePage.clientPacket.postList.get(HomePage.clientPacket.postListSelectedIndex).getClientId().toString());
        }
    }

}*/
