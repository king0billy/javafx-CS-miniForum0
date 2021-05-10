package com.ljl.www.view;

import com.ljl.www.po.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

public class MyInfo {

    @FXML
    private TextField telField;

    @FXML
    private Button saveButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Label IDLabel;

    @FXML
    private Label privilegeLabel;

    @FXML
    private TextField nicknameFiled;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Button logOutButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label enrollDateLabel;

    @FXML
    private TextField addressField;

    @FXML
    //private ChoiceBox<?> sexChoiceBox;
    private ChoiceBox sexChoiceBox;

    @FXML
    private Label sexLabel;

    @FXML
    private Label preSexLabel;
    
    String sex="man";

   public void initialize() {
       IDLabel.setText(Login.clientLocal.getClientId().toString());
        telField.setText(Login.clientLocal.getClientTel());
        nicknameFiled.setText(Login.clientLocal.getClientNickname());
        descriptionArea.setText(Login.clientLocal.getClientDescription());
        privilegeLabel.setText(Login.clientLocal.getClientPrivilege().toString());
        enrollDateLabel.setText(Login.clientLocal.getClientEnrollDate().toString());
        addressField.setText(Login.clientLocal.getClientAddress());
        passwordField.setText(Login.clientLocal.getClientPassword());
        if(sexChoiceBox.getItems().size()==0) {
            sexChoiceBox.getItems().addAll("男", "女","保密","LGBTQ");
        }
        sexChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ov, value, new_value) -> sex = (String) sexChoiceBox.getItems().get((new_value.intValue()))
        );
        preSexLabel.setText(Login.clientLocal.getClientSex());
        sexLabel.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());

        //label.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());
    }
    @FXML
    void eventOnLogOut(ActionEvent event) throws IOException {
        Login.clientLocal=null;
        // TODO: 2021/5/10 不正规 
        MainView.ss.shutdownInput();
        MainView.ss.shutdownOutput();
        MainView.ss.close();
        new Hint().sceneSwitch("Connect2Server.fxml",event);
    }

    @FXML
    void eventOnRefresh(ActionEvent event) {

        initialize();
        Hint.pop("刷新成功");
    }

    @FXML
    void eventOnSave(ActionEvent event) {
        try {
            //写数据
            DataOutputStream out=new DataOutputStream(MainView.ss.getOutputStream());
            //读数据
            DataInputStream in=new DataInputStream(MainView.ss.getInputStream());

            out.writeUTF("editMyInfo");                                        //向服务器说是登陆
            out.flush();

            Login.clientLocal.setClientTel(telField.getText());

            Login.clientLocal.setClientNickname(nicknameFiled.getText());

            Login.clientLocal.setClientSex(sex);
            Login.clientLocal.setClientAddress(addressField.getText());
            Login.clientLocal.setClientDescription(descriptionArea.getText());
            //Login.clientLocal.setClientEnrollDate(enrollDateLabel.getText());
            Login.clientLocal.setClientPrivilege(Long.parseLong(privilegeLabel.getText()));

            ObjectOutputStream oos=new ObjectOutputStream(MainView.ss.getOutputStream());
            oos.writeObject(Login.clientLocal);                                  //将用户信息发过去
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(MainView.ss.getInputStream()));
            Object obj = ois.readObject();
            Client replyClient = (Client) obj;

            if(replyClient.equals(Login.clientLocal)==true){
                Hint.pop("修改成功");
            }
            else{
                Hint.pop("电话号码已被占用or服务器繁忙or毫无改变!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
