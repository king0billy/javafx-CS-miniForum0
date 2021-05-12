package com.ljl.www.view;


import com.ljl.www.po.Client;
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Register {
/**
 * @className Register
 * @description 注册页面控制器
 * @author  22427(king0liam)
 * @date 2021/5/12 17:52
 * @version 1.0
 * @since version-0.0
 */

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
        /**
         * @description 返回登录界面键,页面跳转
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 18:00
         */
        new Hint().sceneSwitch("Login.fxml",event);
    }
    public void eventOnButtonRegister(ActionEvent event) throws IOException {
        /**
         * @description 组测键
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 18:00
         */
        if(passWord1st.getLength()<6||passWord1st.getLength()>20||passWord2nd.getLength()<6||passWord2nd.getLength()>20){
            Hint.pop("密码长度应为6到20个字符！");
            System.out.println("length!!");
        }else{
            if(passWord1st.getText().equals(passWord2nd.getText())==false){
                Hint.pop("两次输入不一致！");
                System.out.println("match!!");
            }
            else {
                try {
                    Client client=new Client(-99L,clientTelField.getText(),passWord1st.getText());
                    //if(Long.parseLong(adminCodeField.getText()).equals(666))client.setClientPrivilege(8L);
                    if(adminCodeField.getText().equals("666"))client.setClientPrivilege(8L);
                    else {client.setClientPrivilege(4L);}

                    Long flag=(Long)Hint.send$Receive("enroll",client);

                    if(flag>0){
                        //用手机号注册,用文本框显示注册的账号
                        Hint.pop(flag);
                        System.out.println("register success,"+flag);
                    }
                    else{
                        Hint.pop("数据库错误或电话已被注册");
                        System.out.println("register failed");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
