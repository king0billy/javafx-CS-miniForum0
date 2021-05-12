package com.ljl.www.view;

import com.ljl.www.util.PostListControlPacket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
/**
 * @className Hint
 * @description view下controller通用的工具类
 * @author  22427(king0liam)
 * @date 2021/5/12 15:37
 * @version 1.0
 * @since version-0.0
 */

public class Hint {

    public static void  pop(String string) {
        /**
         * @description 弹出一行字
         * @exception
         * @param [java.lang.String] [string]
         * @return [java.lang.String]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:38
         */
        Label lbl = new Label(string);//创建面板
        //弃用的代码
/*        Font font=new Font("Monospaced", Font.BOLD,32);//设置字体格式和大小
        lbl.setFont(font);*/
        lbl.setFont(Font.font("Cambria", 20));
        lbl.setWrapText(true);
        FlowPane pane1 = new FlowPane();
        pane1.setHgap(20);
        pane1.setStyle("-fx-background-color:tan;-fx-padding:10px;");//组件加入面板
        pane1.getChildren().addAll(lbl);
        Scene creatingScene = new Scene(pane1, 600, 100);
        Stage PopStage = new Stage();
        //设置为弹窗类型
        PopStage.initModality(Modality.APPLICATION_MODAL);
        PopStage.setScene(creatingScene);
        PopStage.show();
    }
    public static void  pop(Long id) {
        /**
         * @description 为注册成功返回id而设的弹窗,用手机号注册，用文本框显示注册的账号，
         * @exception
         * @param [java.lang.Long] [id]
         * @return [java.lang.Long]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:39
         */
        Label lbl = new Label("注册成功！请复制并保管你的id！");
        TextField textField=new TextField(String.valueOf(id));
        lbl.setFont(Font.font("Cambria", 20));
        lbl.setWrapText(true);
        FlowPane pane1 = new FlowPane();
        pane1.setHgap(20);
        pane1.setStyle("-fx-background-color:tan;-fx-padding:10px;");//组件加入面板
        pane1.getChildren().addAll(lbl,textField);
        Scene creatingScene = new Scene(pane1, 600, 100);
        Stage PopStage = new Stage();
        //设置为弹窗类型
        PopStage.initModality(Modality.APPLICATION_MODAL);
        PopStage.setScene(creatingScene);
        PopStage.show();
    }
    public  void sceneSwitch(String xml,ActionEvent event) throws IOException {
        /**
         * @description 按下某一按键切换场景的通用方法
         * @exception
         * @param [java.lang.String, javafx.event.ActionEvent] [xml, event]
         * @return [java.lang.String, javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 15:39
         */
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(xml)));//只能加载同一包下的？
        Scene creatingScene = new Scene(parent);

        Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        reflectedStage.hide();
        reflectedStage.setScene(creatingScene);
        reflectedStage.show();
    }
    public static Object send$Receive(String utfMessage ,Object object) throws IOException, ClassNotFoundException {
        DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
        out.writeUTF(utfMessage);
        out.flush();

        ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
        oos.writeObject(object);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
        return ois.readObject();
    }
}
