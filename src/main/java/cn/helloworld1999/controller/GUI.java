package cn.helloworld1999.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// 继承Application抽象类，重新start方法
public class GUI extends Application {
    public static void main(String[] args) {
        // 入口函数里调用Application的静态方法launch，之后会自动调用start方法
        Application.launch(args);
    }

    /**
     * @param primaryStage 主窗口
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //Pane root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        Scene scene = new Scene(root,1280,720);
        primaryStage.setTitle("测试 BooK Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
