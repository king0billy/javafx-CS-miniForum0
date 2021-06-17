package com.ljl.www.view;


import com.ljl.www.po.Client;
import com.ljl.www.util.PostListControlPacket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
/**
 * @className Login
 * @description 登录页面的控制器
 * @author  22427(king0liam)
 * @date 2021/5/12 17:01
 * @version 1.0
 * @since version-0.0
 */
public class Login {
    public static Client clientLocal;
    @FXML
    private Button registerButton;

    @FXML
    private TextField clientNameField;

    @FXML
    private TextField clientPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField clientTelField;

    @FXML
    private CheckBox keepPassword;

    public void eventOnButtonLogin(ActionEvent event)throws Exception{
        /**
         * @description 记住密码，登录
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 16:59
         */
        String id=clientNameField.getText();
        String tel=clientTelField.getText();
        String password=clientPasswordField.getText();
        if (keepPassword.isSelected()) {
            // 记住密码 持久化到文件中
            System.out.println("记住密码");
            if (!id.isEmpty() && !password.isEmpty()) {
                Properties prop = new Properties();
                try {
                    FileOutputStream oFile = new FileOutputStream("user.properties", false);
                    prop.setProperty("id", id);
                    prop.setProperty("password",password);
                    prop.setProperty("isSelected",keepPassword.isSelected()+"");
                    prop.store(oFile, null);
                    oFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            try {
                Properties prop = new Properties();
                FileOutputStream oFile = new FileOutputStream("user.properties", false);
                prop.setProperty("isSelected",keepPassword.isSelected()+"");
                prop.store(oFile, null);
                oFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Client client;
            if(id.equals("")){client=new Client(tel,password);}
            else{client=new Client(Long.parseLong(id),tel,password);}

            Client replyClient=(Client)Hint.send$Receive("enter",client);

            if(replyClient.equals(client)==false){
                clientLocal=replyClient;
                new Hint().sceneSwitch("HomePage.fxml",event);
                //Stage reflectedStage = (Stage) ((Node) getClass().getResource("HomePage.fxml")).getScene().getWindow();//null 了
                //Node test1=reflectedStage.getScene().getRoot().lookup("#pulledPost");

//                Node test1=(Node)(FXMLLoader.load(getClass().getResource("HomePage.fxml"))).lookup("#pulledPost");
//                test1.lookup("#refreshList").fireEvent(new ActionEvent());

            }
            else{
                Hint.pop("登录失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void eventOnButtonRegister(ActionEvent event) throws IOException {
        /**
         * @description 切换到注册页面
         * @exception IOException
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 17:03
         */
          new Hint().sceneSwitch("Register.fxml",event);
    }
/*    @Override
    public void initialize(URL location, ResourceBundle resources)*/

    public void initialize(){
        Properties prop = new Properties();
        try {
            if (new File("user.properties").exists()) {
                InputStream in = new BufferedInputStream(new FileInputStream("user.properties"));
                prop.load(in);
                //参考的代码
/*                Iterator<String> it = prop.stringPropertyNames().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    clientNameField.setText(key);
                    clientPasswordField.setText(prop.getProperty(key));
                }*/
                clientNameField.setText( prop.getProperty("id"));
                clientPasswordField.setText( prop.getProperty("password"));
                keepPassword.setSelected(Boolean.parseBoolean(prop.getProperty("isSelected")));
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
