package com.ljl.www.view;

import com.ljl.www.dao.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Hint {
    static void  pop(String shit) {
        Label lbl = new Label(shit);//创建面板
       // Font font=new Font("Monospaced", Font.BOLD,32);//设置字体格式和大小
       // lbl.setFont(font);
        lbl.setFont(Font.font("Cambria", 20));
        lbl.setWrapText(true);
        FlowPane pane1 = new FlowPane();
        pane1.setHgap(20);//set background color of each Pane
        pane1.setStyle("-fx-background-color:tan;-fx-padding:10px;");//组件加入面板
        pane1.getChildren().addAll(lbl);//make 2 scenes from 2 panes
        Scene creatingScene = new Scene(pane1, 300, 100);
    /*Parent parent = FXMLLoader.load(getClass().getResource("PopUpMatc.fxml"));
    Scene creatingScene = new Scene(parent);*/
        Stage PopStage = new Stage();
        PopStage.initModality(Modality.APPLICATION_MODAL);
        PopStage.setScene(creatingScene);
        PopStage.show();
    }
    static void  pop(long id) {
        Label lbl = new Label("注册成功！请复制并保管你的id！");//创建面板
        TextField textField=new TextField(String.valueOf(id));
        lbl.setFont(Font.font("Cambria", 20));
        lbl.setWrapText(true);
        FlowPane pane1 = new FlowPane();
        pane1.setHgap(20);//set background color of each Pane
        pane1.setStyle("-fx-background-color:tan;-fx-padding:10px;");//组件加入面板
        pane1.getChildren().addAll(lbl,textField);//make 2 scenes from 2 panes
        Scene creatingScene = new Scene(pane1, 300, 100);
        Stage PopStage = new Stage();
        PopStage.initModality(Modality.APPLICATION_MODAL);
        PopStage.setScene(creatingScene);
        PopStage.show();
    }
}