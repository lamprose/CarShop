package com.carshop.server.domain;

public class ShoppingCart {

    private Integer cartId;     //购物车ID
    private String id;          //用户ID账号
    private String carId;       //车辆ID
    private Integer amount;     //数量

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
