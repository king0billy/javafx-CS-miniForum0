package com.ljl.www.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Connect2Server {

    @FXML
    private TextField IPField;

    @FXML
    private Button ConnectButton;

    @FXML
    void EventOnButtonConnect(ActionEvent event) {
        try {
            String ip=IPField.getText();
            //ServerSocket serverSocket = new ServerSocket(6666);//【这一步已在server中先运行了！！！】
            MainView.ss=new Socket(ip,6666);//ip输入框
            //MainApp.ss=new Socket(ip,0);
            //MainApp.ss=new Socket(ip,51559);
            //根据ip和端口连接服务器
            if(MainView.ss.isConnected()){                     //如何连接成功

                /*FXMLLoader loader=new FXMLLoader();
                //loader.setLocation(MainView.class.getResource("/view/Login.fxml"));
                loader.setLocation(MainView.class.getResource("Login.fxml"));
                AnchorPane myPane=(AnchorPane)loader.load();

                Scene scene=new Scene(myPane);
                MainView.primaryStage.setScene(scene);
                MainView.primaryStage.show();*/

                Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene creatingScene = new Scene(parent);
                Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
                reflectedStage.hide();
                reflectedStage.setScene(creatingScene);
                reflectedStage.show();
            }
            else{
                    Hint.pop("连接失败");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Hint.pop("连接失败，服务器未开启或ip及端口号错误!!");
        }

    }

}
