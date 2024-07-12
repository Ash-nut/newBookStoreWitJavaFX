package cn.helloworld1999.controller;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.service.impl.SafeImpl;
import cn.helloworld1999.util.GetMapper;
import cn.helloworld1999.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static cn.helloworld1999.controller.SiginNewUser.showOrderDetails;

public class RetrievePassword {

    @FXML
    private AnchorPane SiginPane;

    @FXML
    private TextField inputNewAccount;

    @FXML
    private TextField inputVerificationCode;

    @FXML
    private Button getRe;

    @FXML
    private TextField inputPhoneNum;

    @FXML
    private Button backButton;

    @FXML
    private Button ackButton;

    @FXML
    void getNewAccount(ActionEvent event) {

    }

    @FXML
    void getPhoneNum(ActionEvent event) {

    }

    @FXML
    void getVerificationCode(ActionEvent event) {

    }

    @FXML
    void ackSigin(ActionEvent event) {
        User user = new User();
        user.setAccount(inputNewAccount.getText());
        if (GetMapper.getUserMapper().selectUserByAccount(user) == null) {
            showOrderDetails("查无此人！");
        } else {
            if (inputPhoneNum == null) {
                showOrderDetails("未输入验证码！");
            } else {
                if (!inputVerificationCode.getText().equals(SiginNewUser.re)) {
                    showOrderDetails("验证码有误！");
                } else {
                    user.setUserPhoneNum(inputPhoneNum.getText());
                    user.setAccount(inputNewAccount.getText());
                    user.setPassword(inputVerificationCode.getText());
                    GetMapper.getUserMapper().updateUserSelective(user);
                    GetMapper.commit();
                    showOrderDetails("修改！");
                    Stage stage1 = SceneSwitcher.getStageFromEvent(event);
                    SceneSwitcher.switchScene(stage1, "userPage.fxml");
                }
            }

        }
    }

    @FXML
    void back(ActionEvent event) {
        if (Login.user!=null){
            Stage stage1 = SceneSwitcher.getStageFromEvent(event);
            SceneSwitcher.switchScene(stage1, "userPage.fxml");
        }else {
            Stage stage1 = SceneSwitcher.getStageFromEvent(event);
            SceneSwitcher.switchScene(stage1, "Login.fxml");
        }
    }

    @FXML
    void getRe(ActionEvent event) {
        SafeImpl safe = new SafeImpl();
        SiginNewUser.re = safe.ReCaptcha();
    }

}
