package com.ljl.www.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Stack;

public class MainView extends Application {
    public static Stage primaryStage;
    public static FXMLLoader loader;
    public static Socket ss;

    public static void main(String[] args) throws UnknownHostException {
        launch(args);
        System.out.println("IP地址："+ InetAddress.getLocalHost());
    }
    /*public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void start(Stage primaryStage)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("Connect2Server.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setTitle("TiliTili");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
