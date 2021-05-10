package com.ljl.www.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
