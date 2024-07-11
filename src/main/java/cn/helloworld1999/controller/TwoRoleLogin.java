package cn.helloworld1999.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TwoRoleLogin {

    @FXML
    private AnchorPane twoRoleLogin;

    @FXML
    private Button client;

    @FXML
    private Button merchant;

    @FXML
    void loginUseClientRole(ActionEvent event) {
        System.out.println("跳转到商家页面");
    }

    @FXML
    void loginUseMerchantRole(ActionEvent event) {
        System.out.println("跳转到管理员页面");
    }

}
