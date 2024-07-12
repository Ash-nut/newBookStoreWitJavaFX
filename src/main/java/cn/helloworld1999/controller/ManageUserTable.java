package cn.helloworld1999.controller;

import cn.helloworld1999.bean.Role;
import cn.helloworld1999.bean.User;
import cn.helloworld1999.util.GetMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;

public class ManageUserTable {

    public TextField inputUserId;
    public Button findUser;
    public TextField inputPassword;
    public TextField inputAccount;
    public TextField inputBalance;
    public TextField inputPhoneNum;
    public Button createUser;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> user_id;
    @FXML
    private TableColumn<User, String> account;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, Double> balance;
    @FXML
    private TableColumn<User, String> userPhoneNum;

    private ObservableList<User> userList;

    @FXML
    private void initialize() {
        refreshTable();
        addButtonAndInputField();
    }

    // 刷新表格数据
    private void refreshTable() {
        List<User> userData = getUserData();
        userList = FXCollections.observableArrayList(userData);

        user_id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        userPhoneNum.setCellValueFactory(new PropertyValueFactory<>("userPhoneNum"));

        userTable.setItems(userList);
    }

    // 从数据库获取用户数据
    private List<User> getUserData() {
        return GetMapper.getUserMapper().showAllUsers();
    }

    // 添加按钮和输入框到每行
    private void addButtonAndInputField() {
        TableColumn<User, Void> colBtn = new TableColumn<>("操作");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<>() {

                    private final Button deleteUserBtn = new Button("删除用户");
                    private final Button updatePhoneBtn = new Button("修改电话号");
                    private final TextField inputField = new TextField();

                    {
                        // 设置删除按钮点击事件
                        deleteUserBtn.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            GetMapper.getUserMapper().deleteUserByUserId(user);
                            GetMapper.commit();
                            refreshTable();
                        });

                        // 设置修改电话号按钮点击事件
                        updatePhoneBtn.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            String newPhoneNum = inputField.getText();
                            user.setUserPhoneNum(newPhoneNum);
                            GetMapper.getUserMapper().updateUserSelective(user);
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
                            HBox hBox = new HBox(deleteUserBtn, updatePhoneBtn, inputField);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        userTable.getColumns().add(colBtn);
    }

    public void findUser(ActionEvent actionEvent) {
        User user = new User();
        String strUserId = inputUserId.getText();
        if (strUserId == null || strUserId.isEmpty()) {
            user.setUserId(null);
        } else {
            user.setUserId(Integer.parseInt(strUserId));
        }
        user.setAccount(inputAccount.getText());
        user.setPassword(inputPassword.getText());

        List<User> userData = GetMapper.getUserMapper().selectUserSelective(user);
        userList = FXCollections.observableArrayList(userData);

        userTable.setItems(userList);
    }

    public void inputUserId(ActionEvent actionEvent) {
    }

    public void inputAccount(ActionEvent actionEvent) {
    }

    public void inputPassword(ActionEvent actionEvent) {
    }

    public void inputBalance(ActionEvent actionEvent) {
    }

    public void inputPhoneNum(ActionEvent actionEvent) {
    }

    public void createUser(ActionEvent actionEvent) {
        User user = new User();
        user.setAccount(inputAccount.getText());
        user.setPassword(inputPassword.getText());
        user.setUserPhoneNum(inputPhoneNum.getText());
        GetMapper.getUserMapper().insertUser(user);
        List<User> ul = GetMapper.getUserMapper().selectUserSelective(user);
        Role role = new Role(ul.get(0).getUserId(),"客户");
        GetMapper.getRoleMapper().insertRole(role);
    }
}
