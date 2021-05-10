package com.ljl.www.view;


import com.ljl.www.dao.LoginQuery;
import com.ljl.www.dao.PostListSql;
import com.ljl.www.po.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

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

    public Button getRegisterField() {
        return registerButton;
    }
    public void setClientTelField(TextField clientTelField) {
        this.clientTelField = clientTelField;
    }
    public void setRegisterField(Button registerField) {
        this.registerButton = registerField;
    }

    public TextField getClientNameField() {
        return clientNameField;
    }

    public void setClientNameField(TextField clientNameField) {
        this.clientNameField = clientNameField;
    }

    public TextField getClientPasswordField() {
        return clientPasswordField;
    }

    public void setClientPasswordField(TextField clientPasswordField) {
        this.clientPasswordField = clientPasswordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }


    public void eventOnButtonLogin(ActionEvent event)throws Exception{
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
                    prop.setProperty(id, password);
                    prop.store(oFile, null);
                    oFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        /*if(id!=null){
            if(new LoginQuery().query(id,password)){
                Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene creatingScene = new Scene(parent);
                Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
                reflectedStage.hide();
                reflectedStage.setScene(creatingScene);
                reflectedStage.show();
            }
            else{
                Hint.pop("账号密码输入错误");
            }
        }
        else{
            if(new LoginQuery().queryTel(tel,password)){
                Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene creatingScene = new Scene(parent);
                Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
                reflectedStage.hide();
                reflectedStage.setScene(creatingScene);
                reflectedStage.show();
            }
            else{
                Hint.pop("账号密码输入错误");
            }
        }*/
        try {
            //写数据
            DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
            //读数据
            DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

            out.writeUTF("enter");                                        //向服务器说是登陆
            out.flush();

            Client client;
            if(id.equals("")){client=new Client(tel,password);}
            else{client=new Client(Long.parseLong(id),tel,password);}
                                              //用户信息
            ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
            oos.writeObject(client);                                  //将用户信息发过去
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
            Object obj = ois.readObject();
            Client replyClient = (Client) obj;


            if(replyClient.equals(client)==false){
                //Hint.pop("登陆成功!");
                clientLocal=replyClient;

                new Hint().sceneSwitch("HomePage.fxml",event);

                // PostListSql.setLimit(3);new PostListSql.createPaginationList();

/*                Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene creatingScene = new Scene(parent);
                Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
                reflectedStage.hide();
                reflectedStage.setScene(creatingScene);
                reflectedStage.show();*/
                System.out.println(" new Hint().sceneSwitch(\"HomePage.fxml\",event)");
            }
            else{
                Hint.pop("登录失败!");
            }

            /*int flag=in.read();
            if(flag==1){
                //Hint.pop("登陆成功!");
                clientLocal=client;
                // PostListSql.setLimit(3);new PostListSql.createPaginationList();
                Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene creatingScene = new Scene(parent);
                Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
                reflectedStage.hide();
                reflectedStage.setScene(creatingScene);
                reflectedStage.show();
            }
            else{
                Hint.pop("失败!");
            }*/
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void eventOnButtonRegister(ActionEvent event) throws IOException {
    /*
        Parent Operation_Parent = FXMLLoader.load(getClass().getResource("CreateString.fxml"));
        Scene Operation_Creating_Scene = new Scene(Operation_Parent);
        Stage CreateOperation_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        CreateOperation_Stage.hide();
        CreateOperation_Stage.setScene(Operation_Creating_Scene);
        CreateOperation_Stage.show();
 */     new Hint().sceneSwitch("Register.fxml",event);
/*        Parent parent = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene creatingScene = new Scene(parent);
        Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        reflectedStage.hide();
        reflectedStage.setScene(creatingScene);
        reflectedStage.show();*/
    }
/*    @Override
    public void initialize(URL location, ResourceBundle resources)*/

    public void initialize(){
        Properties prop = new Properties();
        try {
            if (new File("user.properties").exists()) {
                InputStream in = new BufferedInputStream(new FileInputStream("user.properties"));
                prop.load(in);
                Iterator<String> it = prop.stringPropertyNames().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    clientNameField.setText(key);
                    clientPasswordField.setText(prop.getProperty(key));
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eventOnCheckBoxKeepON(ActionEvent event) throws IOException {

    }
    public void eventOnCheckBoxKeepOff(ActionEvent event) throws IOException {

    }

}
