package com.carshop.server.domain;

public class Orders {

    private User user;          //用户ID
    private Cars car;       //车辆ID
    private Integer amount;      //数量
    private String orderStatus; //订单状态

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
}
