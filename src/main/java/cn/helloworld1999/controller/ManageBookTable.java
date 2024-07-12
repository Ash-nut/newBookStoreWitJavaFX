package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Book;
import cn.helloworld1999.bean.Order;
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

import java.util.List;

public class ManageBookTable {

    public TextField bookStock;
    public TextField bookPrice;
    public Button create;
    @FXML
    private TableView<Book> bookTable; // 确保与 FXML 文件中的 fx:id 一致
    @FXML
    private TableColumn<Book, Integer> book_id;
    @FXML
    private TableColumn<Book, String> book_name;
    @FXML
    private TableColumn<Book, Double> price;
    @FXML
    private TableColumn<Book, Integer> stock;

    private ObservableList<Book> booksList2;

    @FXML
    private TextField inputBookId;

    @FXML
    private TextField InputBookName;

    @FXML
    private AnchorPane sel;

    @FXML
    private Button findBook;

    @FXML
    void findBook(ActionEvent event) {
        getBookData();
    }

    @FXML
    void inputBookId(ActionEvent event) {

    }

    @FXML
    void InputBookName(ActionEvent event) {

    }

    // 从数据库获取书籍数据并刷新显示
    private void getBookData() {
        Book book = new Book();
        //book.setBookId(Integer.parseInt(inputBookId.getText()));
        book.setBookName(InputBookName.getText());

        List<Book> bookData = GetMapper.getBookMapper().selectBookSelectiveByBookName(book);

        booksList2 = FXCollections.observableArrayList(bookData);
        book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bookTable.setItems(booksList2);

        addButtonAndInputField();
    }

    // 添加按钮和输入框到每行
    private void addButtonAndInputField() {
        TableColumn<Book, Void> colBtn = new TableColumn<>("操作数量");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<>() {

                    private final Button actionBtn = new Button("删除此书");
                    private final Button cartBtn = new Button("修改库存");
                    private final TextField inputField = new TextField();

                    {
                        // 设置按钮点击事件
                        //点击了 删除此书
                        actionBtn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            GetMapper.getBookMapper().deleteBook(book);
                            GetMapper.commit();
                            getBookData();
                        });
                        // 点击了 修改库存
                        cartBtn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            String inputStr = inputField.getText();
                            if (inputStr.equals("")) {
                                inputField.setText("1");
                            }
                            Integer newStock = Integer.parseInt(inputField.getText());
                            book.setStock(newStock);

                            GetMapper.getBookMapper().updateBook(book);
                            GetMapper.commit();

                            getBookData();
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

    public void inputBookStock(ActionEvent actionEvent) {
    }

    public void inputBookPrice(ActionEvent actionEvent) {
    }

    //点了 create
    public void createBook(ActionEvent actionEvent) {
        Book book = new Book();
        book.setBookName(InputBookName.getText());
        String inputStockStr = bookStock.getText();
        book.setStock(Integer.parseInt(inputStockStr));
        String inputPriceStr = bookPrice.getText();
        book.setPrice(Double.parseDouble(inputPriceStr));
        GetMapper.getBookMapper().insertBook(book);
        GetMapper.commit();
        getBookData();
    }
}
