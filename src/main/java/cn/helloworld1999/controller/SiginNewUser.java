package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.bean.User;
import cn.helloworld1999.service.impl.SafeImpl;
import cn.helloworld1999.util.GetMapper;
import cn.helloworld1999.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SiginNewUser {
    static public String re;

    public Button getRe;
    @FXML
    private AnchorPane SiginPane;

    @FXML
    private TextField inputNewAccount;

    @FXML
    private TextField inputVerificationCode;

    @FXML
    private PasswordField inputPasswordAgain;

    @FXML
    private TextField inputPhoneNum;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button ackButton;

    @FXML
    void getNewAccount(ActionEvent event) {
        User newUser = new User();
        newUser.setAccount(inputNewAccount.getText());
        if (GetMapper.getUserMapper().selectUserByAccount(newUser)!=null){
        showOrderDetails("用户名已存在");
        };
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
        User user = new User();
        user.setAccount(inputNewAccount.getText());
        if (GetMapper.getUserMapper().selectUserByAccount(user)!=null){
            showOrderDetails("用户已存在！");
        }else if (!inputPassword.equals(inputPasswordAgain)){
            showOrderDetails("两次输入的密码不一致！");
        }else if (inputPhoneNum == null){
            showOrderDetails("未输入验证码！");
        }else if (inputVerificationCode.equals(re)){
            showOrderDetails("验证码有误！");
        }else {
            user.setUserPhoneNum(inputPhoneNum.getText());
            user.setAccount(inputNewAccount.getText());
            user.setPassword(inputPassword.getText());
            GetMapper.getUserMapper().insertUser(user);
            Stage stage1 = SceneSwitcher.getStageFromEvent(event);
            SceneSwitcher.switchScene(stage1, "userPage.fxml");
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage stage0 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage0, "login.fxml");
    }
    public void showOrderDetails(String str) {
        // 创建一个信息类型的 Alert 对象
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("警告！"); // 设置弹窗标题
        alert.setHeaderText(null); // 设置弹窗头部文本为空

        // 创建一个 TextArea 来显示订单详情内容
        TextArea textArea = new TextArea(str);
        textArea.setEditable(false); // 设置 TextArea 为不可编辑
        // 将 TextArea 设置为 Alert 的内容
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait(); // 显示弹窗并等待用户关闭
    }

    public void getRe(ActionEvent actionEvent) {
        SafeImpl safeImpl = new SafeImpl();
        re = safeImpl.ReCaptcha();
    }
}
