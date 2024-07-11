package cn.helloworld1999.bean;

import java.util.Date;

public class Order {

    private Integer orderId;    //订单号
    private Integer userId;     //用户id
    private Date orderTime;     //创建时间
    private Double orderSumPrice;   //总价
    private String remark;  //备注
    private String state;   //状态
    public static final String[] STATE = {"待付款","取消","已完成"};

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getOrderSumPrice() {
        return orderSumPrice;
    }

    public void setOrderSumPrice(Double orderSumPrice) {
        this.orderSumPrice = orderSumPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
