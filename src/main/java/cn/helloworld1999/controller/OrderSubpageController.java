package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.bean.User;
import cn.helloworld1999.service.impl.UserBuyBooksImpl;
import cn.helloworld1999.util.GetMapper;
import cn.helloworld1999.util.SceneSwitcher;
import com.almasb.fxgl.entity.action.Action;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class OrderSubpageController {

    @FXML
    private TableView<OrderSubpage> orderSubpageTable;
    @FXML
    private TableColumn<OrderSubpage, String> bookName;
    @FXML
    private TableColumn<OrderSubpage, Integer> userId;
    @FXML
    private TableColumn<OrderSubpage, Integer> num;
    @FXML
    private TableColumn<OrderSubpage, Double> priceOne;
    @FXML
    private TableColumn<OrderSubpage, Double> priceSum;
    @FXML
    private TableColumn<OrderSubpage, String> info;
    @FXML
    private TableColumn<OrderSubpage, Integer> bookId;
    @FXML
    private Button settleOrderButton; // 新增的按钮

    private ObservableList<OrderSubpage> orderSubpageList;

    @FXML
    private void initialize() {
        // 从数据库获取订单子项数据
        List<OrderSubpage> orderSubpageData = getOrderSubpageData(Login.user);
        orderSubpageList = FXCollections.observableArrayList(orderSubpageData);

        // 设置列的单元格值工厂
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        priceOne.setCellValueFactory(new PropertyValueFactory<>("priceOne"));
        priceSum.setCellValueFactory(new PropertyValueFactory<>("priceSum"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        // 将订单子项列表设置到表格中
        orderSubpageTable.setItems(orderSubpageList);
        // 添加按钮和输入框到每行
        addButtonAndInputField();
    }

    // 刷新表格数据
    private void refreshTable() {
        List<OrderSubpage> orderSubpageData = getOrderSubpageData(Login.user);
        orderSubpageList = FXCollections.observableArrayList(orderSubpageData);

        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        priceOne.setCellValueFactory(new PropertyValueFactory<>("priceOne"));
        priceSum.setCellValueFactory(new PropertyValueFactory<>("priceSum"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        orderSubpageTable.setItems(orderSubpageList);
    }

    // 从数据库获取订单子项数据
    private List<OrderSubpage> getOrderSubpageData(User user) {
        return GetMapper.getOrderSubpageMapper().selectShopCarByUser(user);
    }

    // 添加按钮和输入框到每行
    private void addButtonAndInputField() {
        TableColumn<OrderSubpage, Void> colBtn = new TableColumn<>("操作数量");

        Callback<TableColumn<OrderSubpage, Void>, TableCell<OrderSubpage, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<OrderSubpage, Void> call(final TableColumn<OrderSubpage, Void> param) {
                final TableCell<OrderSubpage, Void> cell = new TableCell<>() {

                    private final Button actionBtn = new Button("向订单中加入该项");
                    private final Button cartBtn = new Button("修改该项数量");
                    private final TextField inputField = new TextField();

                    {
                        // 设置按钮点击事件
                        //点击了 向订单中加入该项
                        actionBtn.setOnAction(event -> {
                            OrderSubpage orderSubpage = getTableView().getItems().get(getIndex());
                            if (orderSubpage.getNum()>=0){
                                orderSubpage.setState(OrderSubpage.STATE[2]);
                                GetMapper.getOrderSubpageMapper().updateByOrderSubpageId(orderSubpage);
                                GetMapper.commit();
                                refreshTable();
                            }
                        });
                        // 修改该项数量
                        cartBtn.setOnAction(event -> {
                            OrderSubpage orderSubpage = getTableView().getItems().get(getIndex());
                            String inputStr = inputField.getText();
                            if (inputStr.equals("")) { //啥都不输入就啥都不改
                                inputField.setText("0");
                            }
                            Integer inputNum = Integer.parseInt(inputField.getText());
                            inputNum = inputNum >0? inputNum:0;
                            orderSubpage.setNum(inputNum);
                            GetMapper.getOrderSubpageMapper().updateByOrderSubpageId(orderSubpage);
                            GetMapper.commit();
                            refreshTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(actionBtn, cartBtn, inputField);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        orderSubpageTable.getColumns().add(colBtn);
    }

    // 结算订单按钮的点击事件处理方法
    @FXML
    private void settleOrder(ActionEvent event) {
        UserBuyBooksImpl userBuyBooksImpl = new UserBuyBooksImpl();
        userBuyBooksImpl.createOrder(Login.user);
        GetMapper.commit();
        // 没付款的全都一起付款了吧 hahaha
        List<Order> ol = GetMapper.getOrderMapper().getSomeOrders(Login.user);
        for (Order o : ol) {
            if (o.getState().equals("待付款")) {
                userBuyBooksImpl.pay(Login.user, o);
            }
        }
        refreshTable();
        Stage stage0 = SceneSwitcher.getStageFromEvent(event);
        SceneSwitcher.switchScene(stage0, "userPage.fxml");
    }
}
