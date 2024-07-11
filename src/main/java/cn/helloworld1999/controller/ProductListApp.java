package cn.helloworld1999.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建商品列表
        ObservableList<String> products = FXCollections.observableArrayList(
                "商品1", "商品2", "商品3", "商品4"
        );
        ListView<String> listView = new ListView<>(products);

        // 创建输入框
        TextField inputField = new TextField();
        inputField.setPromptText("输入商品名称");

        // 创建按钮
        Button addButton = new Button("添加商品");
        Button detailsButton = new Button("查看详情");

        // 添加按钮事件处理
        addButton.setOnAction(event -> {
            String newProduct = inputField.getText();
            if (!newProduct.isEmpty()) {
                products.add(newProduct);
                inputField.clear();
            }
        });

        detailsButton.setOnAction(event -> {
            String selectedProduct = listView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                System.out.println("查看详情: " + selectedProduct);
            }
        });

        // 将输入框和按钮添加到 VBox
        VBox controlBox = new VBox(10, inputField, addButton, detailsButton);

        // 将 ListView 和控制面板组合到 HBox
        HBox root = new HBox(10, listView, controlBox);

        // 设置场景和舞台
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("商品列表");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
