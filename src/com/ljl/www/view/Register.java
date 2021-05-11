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
            else {
               /* Long r4return=new RegisterSql().insert(clientTelField.getText(),passWord1st.getText());
               if(r4return>0){
                   Hint.pop(r4return);
                   System.out.println("success,"+r4return);//用手机号注册或者，用文本框显示注册的账号，
               }
               else{
                   Hint.pop("数据库错误或电话已被注册");
                   System.out.println("failed");
               }//提示数据库错误

                */
                try {
                    //写数据
                    DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
                    //读数据
                    DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

                    out.writeUTF("enroll");                                //给服务器端发送注册
                    out.flush();

                    ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
                    Client client=new Client(-99L,clientTelField.getText(),passWord1st.getText());
                    //if(Long.parseLong(adminCodeField.getText()).equals(666))client.setClientPrivilege(8L);
                    if(adminCodeField.getText().equals("666"))client.setClientPrivilege(8L);
                    else {client.setClientPrivilege(4L);}

                    oos.writeObject(client);                                  //将用户信息发过去
                    oos.flush();

                    Long flag=in.readLong();
                    if(flag>0){
                        Hint.pop(flag);
                        System.out.println("success,"+flag);//用手机号注册或者，用文本框显示注册的账号，
                    }
                    else{
                        Hint.pop("数据库错误或电话已被注册");
                        System.out.println("failed");
                    }//提示数据库错误
                    //LoginControl.mystage.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
