package com.carshop.server.domain;

public class Cars {

    private String carId;           //车辆ID
    private String carName;         //车辆名
    private double price;           //价格
    private String image;           //配图
    private String evaluation;      //总评
    private Brands brand;           //品牌ID
    private Parameters param;       //参数ID

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public Parameters getParam() {
        return param;
    }

    public void setParam(Parameters param) {
        this.param = param;
    }
}
