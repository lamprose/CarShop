package com.carshop.server.domain;

public class Orders {

    private Integer orderId;    //订单ID
    private User user;          //用户ID
    private Cars car;           //车辆ID
    private Integer amount;     //数量
    private String orderStatus; //订单状态
    private String orderTime;   //下单时间

    public Orders(User user, Cars car, Integer amount, String orderStatus, String orderTime) {
        this.user = user;
        this.car = car;
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }

    public Orders() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", car=" + car +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
