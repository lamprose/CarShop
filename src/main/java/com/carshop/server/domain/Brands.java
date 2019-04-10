package com.carshop.server.domain;

public class Brands {

    private String brandId;         //品牌ID
    private String brandName;       //品牌名
    private String display;         //展示图
    private String logo;            //商标

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brandId='" + brandId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", display='" + display + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
