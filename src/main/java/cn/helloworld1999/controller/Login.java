package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Role;
import cn.helloworld1999.bean.User;
import cn.helloworld1999.util.GetMapper;
import cn.helloworld1999.util.SceneSwitcher;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import cn.helloworld1999.service.impl.LoginImpl;

import java.io.IOException;
import java.util.List;


public class Login {
    static public int rolePower;
    static public User user = new User(); // Controller 层的 登录的用户的实例

    @FXML
    private Button exitButton;

    @FXML
    private Button lost;

    @FXML
    private Button loginButton;

    @FXML
    private Button signInButton;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputAccount;

    /**
     * 点击 登录 按钮，在这里拼装对象，然后传给 Service 的 Login
     * @param event
     */
    @FXML
    void loginButton(ActionEvent event) throws IOException {
        //构造待检测的登陆对象
        User user = new User(inputAccount.getText(), inputPassword.getText());
        // 在这里 调用 Service 的登录功能，如果登录成功则...如果失败则...
        LoginImpl login = new LoginImpl();
        Integer key = login.login(user);
        //无此账号，跳转注册界面
        if (key == -1) {
            Stage stage = SceneSwitcher.getStageFromEvent(event);
            SceneSwitcher.switchScene(stage, "siginNewUser.fxml");
        }
        //密码错误
        else if (key == -2) {
            Stage stage = SceneSwitcher.getStageFromEvent(event);
            SceneSwitcher.switchScene(stage, "RetrievePassword.fxml");
        }
        //登录成功了
        else {
            user.setUserId(key);
            // 从这一刻开始，user 有了灵魂（笑）
            Login.user = GetMapper.getUserMapper().selectUserByUserId(user);
            //获取 User 拥有的角色
            Role role = new Role(key,null);
            List<Role> roleList = GetMapper.getRoleMapper().selectSomeRole(role);
            // 角色数量必然不小于1，因为默认就会有一个角色是 客户
            switch (roleList.size()){
                case 1:
                    System.out.println("登录客户页面");
                    Login.rolePower = 1; //算是一个魔法值吧，它其实是用来保证权限的，防止出现利用重置密码的返回页面进行提权
                    Stage stage0 = SceneSwitcher.getStageFromEvent(event);
                    SceneSwitcher.switchScene(stage0, "userPage.fxml");
                    break;
                case 2:
                    System.out.println("登录2角色选择页面");
                    Stage stage1 = SceneSwitcher.getStageFromEvent(event);
                    SceneSwitcher.switchScene(stage1, "twoRolePage.fxml");
                    break;
                case 3:
                    System.out.println("登录3角色选择页面");
                    Stage stage2 = SceneSwitcher.getStageFromEvent(event);
                    SceneSwitcher.switchScene(stage2, "threeRoleLogin.fxml");
                    break;
                default:
                    System.out.println("糟糕！程序以意想不到的问题运行了，绝对不是程序员的锅");
                    break;
            }
        }

    }

    @FXML
    void signIn(ActionEvent event) {
        System.out.println("注册");
        Stage stage = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage, "siginNewUser.fxml");
    }

    @FXML
    void lost(ActionEvent event) {
        System.out.println("忘记密码");
        Stage stage = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage, "RetrievePassword.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
        System.out.println("退出");
        System.exit(0);
    }

    @FXML
    void inputPassword(ActionEvent event) {
    }

    @FXML
    void inputAccount(ActionEvent event) {
    }

    public void loginPane(MouseEvent mouseEvent) {
    }

    public static class GUI extends Application {
        private static final int WINDOW_HEIGHT = 1280;
        private static final int WINDOW_WIDTH = 720;


        public void start(Stage stage) throws Exception {
           //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));  //登陆界面
           //Parent root = FXMLLoader.load(getClass().getResource("siginNewUser.fxml")); //注册界面
           //Parent root = FXMLLoader.load(getClass().getResource("retrievePassword.fxml")); //重置密码
           Parent root = FXMLLoader.load(getClass().getResource("retrievePassword.fxml")); //重置密码

           Scene scene = new Scene(root, WINDOW_HEIGHT, WINDOW_WIDTH);
           stage.setScene(scene);
           stage.setTitle("Book Store");
           stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
