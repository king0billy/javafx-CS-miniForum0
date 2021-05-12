package com.ljl.www.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * @className AnotherClient
 * @description 当帖子作者不是本账号登陆者时显示的他人信息页面
 * @author  22427(king0liam)
 * @date 2021/5/12 15:22
 * @version 1.0
 * @since version-0.0
 */
public class AnotherClient {
    @FXML
    private Label sexLabel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label IDLabel;

    @FXML
    private Button refreshButton;

    @FXML
    private Label privilegeLabel;

    @FXML
    private TextField nicknameFiled;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label preSexLabel;

    @FXML
    private TextField telField;

    @FXML
    private Label enrollDateLabel;

    @FXML
    private TextField addressField;

    @FXML
    private ChoiceBox<?> sexChoiceBox;

    @FXML
    void eventOnRefresh(ActionEvent event) {
        initialize();
        Hint.pop("刷新成功");
    }

    public void initialize() {
        IDLabel.setText(PostDetailPage.author.getClientId().toString());
        telField.setText(PostDetailPage.author.getClientTel());
        nicknameFiled.setText(PostDetailPage.author.getClientNickname());
        descriptionArea.setText(PostDetailPage.author.getClientDescription());
        privilegeLabel.setText(PostDetailPage.author.getClientPrivilege().toString());
        enrollDateLabel.setText(PostDetailPage.author.getClientEnrollDate().toString());
        addressField.setText(PostDetailPage.author.getClientAddress());
        passwordField.setText(PostDetailPage.author.getClientPassword());

        preSexLabel.setText(PostDetailPage.author.getClientSex());


        //label.textProperty().bind(sexChoiceBox.getSelectionModel().selectedItemProperty());
    }
}
