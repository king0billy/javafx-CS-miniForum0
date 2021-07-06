package com.ljl.www.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Stack;
/**
 * @className MainView
 * @description 主程序
 * @author  22427(king0liam)
 * @date 2021/6/18 17:05
 * @version 1.0
 * @since version-0.0
 */
public class MainView extends Application {
    public static Socket ss;

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("本机\"IP\"地址："+ InetAddress.getLocalHost());
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        /**
         * @description 重写start
         * @exception IOException
         * @param [javafx.stage.Stage] [primaryStage]
         * @return [javafx.stage.Stage]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 17:06
         */
        Parent root= FXMLLoader.load(getClass().getResource("Connect2Server.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setTitle("C/S的javafx论坛");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
