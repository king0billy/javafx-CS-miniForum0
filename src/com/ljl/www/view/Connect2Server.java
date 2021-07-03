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
import java.util.Objects;
/**
 * @className Connect2Server
 * @description 客户端见到的第一个页面,先与服务端建立socket连接
 * @author  22427(king0liam)
 * @date 2021/6/18 15:30
 * @version 1.0
 * @since version-0.0
 */
public class Connect2Server {

    @FXML
    private TextField IPField;

    @FXML
    private Button ConnectButton;

    @FXML
    void EventOnButtonConnect(ActionEvent event) {
        /**
         * @description 连接键绑定的事件
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 15:31
         */
        try {
            String ip=IPField.getText();
            //ServerSocket serverSocket = new ServerSocket(6666);//【这一步已在server中先运行了！！！】
            MainView.ss=new Socket(ip,6666);//ip输入框
            //根据ip和端口连接服务器
            if(MainView.ss.isConnected()){
                new Hint().sceneSwitch("Login.fxml",event);
            }
            else{
                    Hint.pop("连接失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Hint.pop("连接失败，服务器未开启或ip及端口号错误!!");
        }

    }

}
