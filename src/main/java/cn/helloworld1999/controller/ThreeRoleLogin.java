package cn.helloworld1999.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ThreeRoleLogin {

    @FXML
    private Button client;

    @FXML
    private Button merchant;

    @FXML
    private Button admin;

    @FXML
    void loginUseClientRole(ActionEvent event) {
        System.out.println("进入客户界面");
    }

    @FXML
    void loginUseMerchantRole(ActionEvent event) {
        System.out.println("进入商家界面");
    }

    @FXML
    void loginUseAdminRole(ActionEvent event) {
        System.out.println("进入管理员界面");
    }

}

