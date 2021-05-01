package com.ljl.www.view;


import com.ljl.www.dao.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

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

    /*private class myEventHandler implements EventHandler<ActionEvent>{
                @Override
                public void handle(ActionEvent event) {

                }
            }*/
    public void eventOnButtonLogin(ActionEvent event)throws Exception{
        String id=clientNameField.getText();
        String tel=clientTelField.getText();
        String password=clientPasswordField.getText();
        if(id!=null){
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
        }
    }
    /*public void eventOnButtonRegister()throws Exception{
        //String text = text_1.getText();//获取文本框输入的内容

        Parent root= FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setTitle("注册&登录界面");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("fuck u!!");
    }*/
    public void eventOnButtonRegister(ActionEvent event) throws IOException {
    /*
        Parent Operation_Parent = FXMLLoader.load(getClass().getResource("CreateString.fxml"));
        Scene Operation_Creating_Scene = new Scene(Operation_Parent);
        Stage CreateOperation_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        CreateOperation_Stage.hide();
        CreateOperation_Stage.setScene(Operation_Creating_Scene);
        CreateOperation_Stage.show();
 */
        Parent parent = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene creatingScene = new Scene(parent);
        Stage reflectedStage = (Stage) ((Node) event.getSource()).getScene().getWindow();//【反射？】event转node然后一路get window
        reflectedStage.hide();
        reflectedStage.setScene(creatingScene);
        reflectedStage.show();
    }


}
