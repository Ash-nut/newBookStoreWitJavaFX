package cn.helloworld1999.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SiginNewUser {
    @FXML
    private TextField inputNewAccount;

    @FXML
    private TextField inputVerificationCode;

    @FXML
    private PasswordField inputPasswordAgain;

    @FXML
    private TextField inputPhoneNum;

    @FXML
    private AnchorPane SiginNuewUser;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button ackButton;

    @FXML
    void forgetThePassword(ActionEvent event) {

    }

    @FXML
    void getNewAccount(ActionEvent event) {

    }

    @FXML
    void getNewPassword(ActionEvent event) {

    }

    @FXML
    void confirmPassword(ActionEvent event) {

    }

    @FXML
    void getPhoneNum(ActionEvent event) {

    }

    @FXML
    void getVerificationCode(ActionEvent event) {

    }

    @FXML
    void ackSigin(ActionEvent event) {
        System.out.println("确认注册，信息如下：");
    }

    @FXML
    void back(ActionEvent event) {
        System.out.println("返回");
    }

}
