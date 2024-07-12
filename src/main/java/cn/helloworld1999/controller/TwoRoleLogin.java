package cn.helloworld1999.controller;

import cn.helloworld1999.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TwoRoleLogin {

    @FXML
    private AnchorPane twoRoleLogin;

    @FXML
    private Button client;

    @FXML
    private Button merchant;

    @FXML
    void loginUseClientRole(ActionEvent event) {
        Stage stage1 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage1, "userPage.fxml");
    }

    @FXML
    void loginUseMerchantRole(ActionEvent event) {
        System.out.println("跳转到管理员页面");
        Stage stage1 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage1, "merchant.fxml");
    }

}
