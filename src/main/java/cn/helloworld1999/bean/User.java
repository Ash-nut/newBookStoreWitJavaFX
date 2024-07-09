package cn.helloworld1999.bean;

public class User {
    private Integer userId = null;
    private String account = null;
    private String password = null;
    private Double balance = 0.0;
    private Integer role = 0;   //废弃，这个参数是和 role 进行一对多的，所以其实可以 只留下一个 userId,这里先不删，等有空再删
    private String userPhoneNum = null;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    /**
     * 这个无参构造是用于测试用例编写的，虽然现在并不能用测试框架了
     */
    public User() {

    }

    /**
     * 这个构造方法仅用于数据库操作，id 是数据库自动分配的
     *
     * @param account      用户名
     * @param password     密码
     * @param balance      余额
     * @param role         角色,但是这个是被废弃的属性，所以无所谓
     * @param userPhoneNum 电话号码
     */
    public User(String account, String password, Double balance, Integer role, String userPhoneNum) {
        this.account = account;
        this.password = password;
        this.balance = balance;
        this.role = role;
        this.userPhoneNum = userPhoneNum;
    }
}
