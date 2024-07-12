package cn.helloworld1999.controller;

import cn.helloworld1999.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ThreeRoleLogin {

    @FXML
    private Button client;

    @FXML
    private Button merchant;

    @FXML
    private Button admin;

    @FXML
    void loginUseClientRole(ActionEvent event) {
        Stage stage = SceneSwitcher.getStageFromEvent(event);
        Login.rolePower = 1;
        SceneSwitcher.switchScene(stage, "userPage.fxml");
    }

    @FXML
    void loginUseMerchantRole(ActionEvent event) {
        System.out.println("进入商家界面");
        Login.rolePower = 2;
        Stage stage = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage, "merchant.fxml");
    }

    @FXML
    void loginUseAdminRole(ActionEvent event) {
        System.out.println("进入管理员界面");
        Login.rolePower = 3;
        Stage stage = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage, "AdminPage.fxml");
    }

}

