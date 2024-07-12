package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.service.impl.UserBuyBooksImpl;
import cn.helloworld1999.util.GetMapper;
import cn.helloworld1999.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class mePageController {

    @FXML
    private Button toUP;

    @FXML
    private Button reLogin;

    @FXML
    private AnchorPane me;

    @FXML
    private TableView<Order> orderList;
    @FXML
    private TableColumn<Order, Integer> order_id;
    @FXML
    private TableColumn<Order, String> order_date; // 修改为 String 类型
    @FXML
    private TableColumn<Order, Double> order_sum_price;
    @FXML
    private TableColumn<Order, String> remark;
    @FXML
    private TableColumn<Order, String> state;
    @FXML
    private TableColumn<Order, Void> colBtn; // 添加这一行

    private ObservableList<Order> orderList1;

    @FXML
    private TextField inPutInUp;

    @FXML
    private Button changePassword;

    @FXML
    void inPutInUp(ActionEvent event) {
    }

    @FXML
    void toUP(ActionEvent event) {
        // 处理充值按钮点击的逻辑
        Double toUpMoney = Double.parseDouble(inPutInUp.getText());
        toUpMoney = Math.abs(toUpMoney);
        UserBuyBooksImpl userBuyBooksImpl = new UserBuyBooksImpl();
        userBuyBooksImpl.topUp(Login.user, toUpMoney);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("充值信息");
        alert.setHeaderText(null);
        alert.setContentText("充值用户 " + Login.user.getUserId() + "\n" +
                "充值金额：" + toUpMoney + "\n" +
                "当前余额：" + Login.user.getBalance()
        );
        alert.showAndWait();
    }

    @FXML
    void reLogin(ActionEvent event) {
        // 处理注销登录按钮点击的逻辑
        Stage stage0 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage0, "login.fxml");
    }

    @FXML
    void changePassword(ActionEvent event) {
        // 处理修改密码按钮点击的逻辑
        Stage stage0 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage0, "retrievePassword.fxml");
    }

    @FXML
    private void initialize() {
        System.out.println("Initializing mePageController...");
        try {
            getOrderData();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOrderData() {
        System.out.println("Loading order data...");
        List<Order> orderData = GetMapper.getOrderMapper().getSomeOrders(Login.user);
        if (orderData != null) {
            System.out.println("Order data size: " + orderData.size());
        } else {
            System.out.println("Order data is null.");
        }

        orderList1 = FXCollections.observableArrayList(orderData);
        order_id.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        order_date.setCellValueFactory(new PropertyValueFactory<>("orderDateFormatted")); // 使用格式化后的日期
        order_sum_price.setCellValueFactory(new PropertyValueFactory<>("orderSumPrice"));
        remark.setCellValueFactory(new PropertyValueFactory<>("remark"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        orderList.setItems(orderList1);

        addButtonAndInputField();
    }

    private void addButtonAndInputField() {
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                final TableCell<Order, Void> cell = new TableCell<>() {

                    private final Button actionBtn = new Button("预留功能");
                    private final Button cartBtn = new Button("查看订单");

                    {
                        // 无事发生，因为没设置
                        actionBtn.setOnAction(event -> {
                        });

                        cartBtn.setOnAction(event -> {
                            Order order = getTableView().getItems().get(getIndex());
                            showOrderDetails(order);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(actionBtn, cartBtn);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory); // 设置 cellFactory 到 colBtn
        orderList.getColumns().add(colBtn); // 将 colBtn 添加到 orderList 的列中
    }


    /**
     * 显示订单详情的弹窗
     *
     * @param order 要显示详情的订单对象
     */
    private void showOrderDetails(Order order) {
        // 创建一个信息类型的 Alert 对象
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("订单详情"); // 设置弹窗标题
        alert.setHeaderText(null); // 设置弹窗头部文本为空

        // 使用 StringBuilder 构建订单详情内容
        StringBuilder content = new StringBuilder();
        content.append("订单ID: ").append(order.getOrderId()).append("\n") // 添加订单ID
                .append("订单状态: ").append(order.getState()).append("\n") // 添加订单状态
                .append("订单创建日期: ").append(order.getOrderDateFormatted()).append("\n") // 添加订单创建日期
                .append("订单总价: ").append(order.getOrderSumPrice()).append("\n") // 添加订单总价
                .append("商品列表:\n"); // 添加商品列表标题

        // 获取订单的子页面列表
        List<OrderSubpage> os = GetMapper.getOrderSubpageMapper().getAllOrderSubpageByOrder(order);
        // 初始化 osStr 列表
        List<String> osStr = new ArrayList<>();
        for (OrderSubpage o : os) {
            osStr.add(o.toString());
        }

        // 遍历订单中的商品列表，并将每个商品添加到内容中
        for (String item : osStr) {
            content.append(item).append("\n"); // 每个商品占一行
        }

        // 创建一个 TextArea 来显示订单详情内容
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false); // 设置 TextArea 为不可编辑
        textArea.setWrapText(true); // 设置 TextArea 自动换行

        // 将 TextArea 设置为 Alert 的内容
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait(); // 显示弹窗并等待用户关闭
    }


}
