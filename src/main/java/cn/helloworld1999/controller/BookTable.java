package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Book;
import cn.helloworld1999.bean.Order;
import cn.helloworld1999.service.impl.UserBuyBooksImpl;
import cn.helloworld1999.util.GetMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;

public class BookTable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> book_id;
    @FXML
    private TableColumn<Book, String> book_name;
    @FXML
    private TableColumn<Book, Double> price;
    @FXML
    private TableColumn<Book, Integer> stock;

    private ObservableList<Book> booksList;

    @FXML
    private void initialize() {
        // 从一个 List<Book> 中读取数据
        List<Book> bookData = getBookData();
        booksList = FXCollections.observableArrayList(bookData);

        // 设置列的单元格值工厂
        book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // 设置列的单元格工厂，使其可编辑
        book_name.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        stock.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.IntegerStringConverter()));

        // 将书籍列表设置到表格中
        bookTable.setItems(booksList);

        // 添加按钮和输入框到每行
        addButtonAndInputField();
    }

    // 从数据库获取书籍数据
    private List<Book> getBookData() {
        return GetMapper.getBookMapper().selectAllBook();
    }

    // 添加按钮和输入框到每行
    private void addButtonAndInputField() {
        TableColumn<Book, Void> colBtn = new TableColumn<>("操作数量");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<>() {

                    private final Button actionBtn = new Button("立即购买");
                    private final Button cartBtn = new Button("加入购物车");
                    private final TextField inputField = new TextField();

                    {
                        // 设置按钮点击事件
                        //点击了 立即购买
                        actionBtn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            String inputStr = inputField.getText();
                            if (inputStr.equals("")) {
                                inputField.setText("1");
                            }
                            Integer inputNum = Integer.parseInt(inputField.getText());

                            UserBuyBooksImpl userBuyBooksImpl = new UserBuyBooksImpl();
                            userBuyBooksImpl.createOrderSubpage(Login.user, book, 2, inputNum);
                            //至此为止，行为正常
                            GetMapper.commit();
                            userBuyBooksImpl.createOrder(Login.user);
                            GetMapper.commit();
                            // 没付款的全都一起付款了吧 hahaha
                            List<Order> ol = GetMapper.getOrderMapper().getSomeOrders(Login.user);
                            for (Order o : ol) {
                                if (o.getState().equals("待付款")) {
                                    userBuyBooksImpl.pay(Login.user, o);
                                }
                            }
                        });
                        // 点击了加入购物车
                        cartBtn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            String inputStr = inputField.getText();
                            if (inputStr.equals("")) {
                                inputField.setText("1");
                            }
                            Integer inputNum = Integer.parseInt(inputField.getText());
                            UserBuyBooksImpl userBuyBooksImpl = new UserBuyBooksImpl();
                            userBuyBooksImpl.createOrderSubpage(Login.user, book, 1, inputNum);
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
        bookTable.getColumns().add(colBtn);
    }
}
