package com.ljl.www.view;

import com.ljl.www.dao.RegisterSql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Register {


    @FXML
    private PasswordField passWord1st;

    @FXML
    private TextField clientTelField;

    @FXML
    private PasswordField adminCodeField;

    @FXML
    private Button backToLogin;

    @FXML
    private PasswordField passWord2nd;

    @FXML
    private Button enroll;


    public void eventOnButtonBackToLogin(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene creatingScene = new Scene(parent);
        Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        reflectedStage.hide();
        reflectedStage.setScene(creatingScene);
        reflectedStage.show();
    }
    public void eventOnButtonRegister(ActionEvent event) throws IOException {
        if(passWord1st.getLength()<6||passWord1st.getLength()>20||passWord2nd.getLength()<6||passWord2nd.getLength()>20){
            Hint.pop("密码长度应为6到20个字符！");
            System.out.println("length!!");
        }else{
            if(passWord1st.getText().equals(passWord2nd.getText())==false){
                Hint.pop("两次输入不一致！");
                System.out.println("match!!");
            }
            else if(true){
                Long r4return=new RegisterSql().insert(clientTelField.getText(),passWord1st.getText());
               if(r4return!=0){
                   Hint.pop(r4return);
                   System.out.println("success,"+r4return);//用手机号注册或者，用文本框显示注册的账号，
               }
               else{
                   Hint.pop("数据库错误或电话已被注册");
                   System.out.println("failed");
               }//提示数据库错误
            }
        }
    }
}
