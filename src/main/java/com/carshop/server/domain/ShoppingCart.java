package com.carshop.server.domain;

public class ShoppingCart {

    private Integer cartId;     //购物车ID
    private User user;          //用户ID账号
    private Cars car;           //车辆ID
    private Integer amount;     //数量

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", car=" + car +
                ", amount=" + amount +
                '}';
    }
}
