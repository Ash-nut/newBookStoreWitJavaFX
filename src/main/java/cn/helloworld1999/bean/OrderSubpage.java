package cn.helloworld1999.bean;

public class OrderSubpage {
    private Integer orderSubpageId;     //子项id，自动生成
    private Integer orderId;    //所属订单id
    private String bookName;
    private Integer userId;
    private Integer num;
    private String state;   //子订单状态 (待删除 未选中、选中、待付款、订单完成)
    private Double priceOne;
    private Double priceSum;
    private String info;    //信息
    private Integer bookId;
    static public final String[] STATE = {"主订单取消","购物车", "待生成订单", "已生成订单", "订单完成"};

    @Override
    public String toString() {
        return "OrderSubpage{" +
                "orderSubpageId=" + orderSubpageId +
                ", orderId=" + orderId +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", num=" + num +
                ", state='" + state + '\'' +
                ", priceOne=" + priceOne +
                ", priceSum=" + priceSum +
                ", info='" + info + '\'' +
                ", bookId=" + bookId +
                '}';
    }

    /**
     * 自动计算子订单总价的构造方法
     * @param userId 所属用户id
     * @param orderId 所属订单id
     * @param bookName 书籍名称
     * @param num 数量
     * @param state 子订单状态 ("待删除","未选中", "选中", "待付款", "订单完成"，STATE.[0-4])
     * @param priceOne 单价
     * @param info 信息
     * @param bookId 书籍id
     */
    public OrderSubpage(Integer userId,Integer orderId, String bookName, Integer num, String state, Double priceOne, String info, Integer bookId) {
        this.userId = userId;
        this.orderId = orderId;
        this.bookName = bookName;
        this.num = num;
        this.state = state;
        this.priceOne = priceOne;
        this.priceSum = priceOne * num;
        this.info = info;
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderSubpageId() {
        return orderSubpageId;
    }

    public void setOrderSubpageId(Integer orderSubpageId) {
        this.orderSubpageId = orderSubpageId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Double priceOne) {
        this.priceOne = priceOne;
    }

    public Double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
