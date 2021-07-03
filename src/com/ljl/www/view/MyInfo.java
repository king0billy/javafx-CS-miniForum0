package com.ljl.www.view;

import com.ljl.www.po.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
/**
 * @className MyInfo
 * @description 本人信息的展示页
 * @author  22427(king0liam)
 * @date 2021/6/18 17:13
 * @version 1.0
 * @since version-0.0
 */
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
       /**
        * @description 初始化此页面
        * @exception
        * @param [] []
        * @return []
        * @since version-1.0
        * @author 22427(king0liam)
        * @date 2021/6/18 17:13
        */
        IDLabel.setText(Login.clientLocal.getClientId().toString());
        telField.setText(Login.clientLocal.getClientTel());
        nicknameFiled.setText(Login.clientLocal.getClientNickname());
        descriptionArea.setText(Login.clientLocal.getClientDescription());
        privilegeLabel.setText(Login.clientLocal.getClientPrivilege().toString());
        enrollDateLabel.setText(Login.clientLocal.getClientEnrollDate().toString());
        addressField.setText(Login.clientLocal.getClientAddress());
        passwordField.setText(Login.clientLocal.getClientPassword());
        //第一次加载此页面
        if(sexChoiceBox.getItems().size()==0) {
            sexChoiceBox.getItems().addAll("男", "女","保密","LGBTQ");
        }
        sexChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ov, value, new_value) -> sex = (String) sexChoiceBox.getItems().get((new_value.intValue()))
        );
        preSexLabel.setText(Login.clientLocal.getClientSex());
        sexLabel.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());
    }
    @FXML
    void eventOnLogOut(ActionEvent event) throws IOException {
        Login.clientLocal=null;
        // TODO: 2021/5/10 不正规的断连方式
        MainView.ss.shutdownInput();
        MainView.ss.shutdownOutput();
        MainView.ss.close();
        new Hint().sceneSwitch("Connect2Server.fxml",event);
    }

    @FXML
    void eventOnRefresh(ActionEvent event) {
        /**
         * @description 绑定刷新键
         * @exception
         * @param [javafx.event.ActionEvent] [event]
         * @return [javafx.event.ActionEvent]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 17:14
         */
        initialize();
        Hint.pop("刷新成功");
    }

    @FXML
    void eventOnSave(ActionEvent event) {
       /**
        * @description 保存修改
        * @exception
        * @param [javafx.event.ActionEvent] [event]
        * @return [javafx.event.ActionEvent]
        * @since version-1.0
        * @author 22427(king0liam)
        * @date 2021/6/18 17:14
        */
        try {
            Login.clientLocal.setClientTel(telField.getText());
            Login.clientLocal.setClientNickname(nicknameFiled.getText());
            Login.clientLocal.setClientPassword(passwordField.getText());
            Login.clientLocal.setClientSex(sex);
            Login.clientLocal.setClientAddress(addressField.getText());
            Login.clientLocal.setClientDescription(descriptionArea.getText());
            //Login.clientLocal.setClientEnrollDate(enrollDateLabel.getText());
            Login.clientLocal.setClientPrivilege(Long.parseLong(privilegeLabel.getText()));

            Client replyClient = (Client) Hint.send$Receive("editMyInfo",Login.clientLocal);

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
