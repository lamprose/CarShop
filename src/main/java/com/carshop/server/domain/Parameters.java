package com.carshop.server.domain;

public class Parameters {

    private Integer paraId;          //参数ID
    private String carSize;         //车身尺寸
    private String engine;          //发动机
    private String level;           //级别
    private String structure;       //车身结构
    private String assurance;       //整车质保
    private double oilWear;         //油耗
    private String manufacturer;    //厂商
    private String gearbox;         //变速箱
    private Integer maxSpeed;        //最高车速

    public Integer getParaId() {
        return paraId;
    }

    public void setParaId(Integer paraId) {
        this.paraId = paraId;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public double getOilWear() {
        return oilWear;
    }

    public void setOilWear(double oilWear) {
        this.oilWear = oilWear;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "paraId='" + paraId + '\'' +
                ", carSize='" + carSize + '\'' +
                ", engine='" + engine + '\'' +
                ", level='" + level + '\'' +
                ", structure='" + structure + '\'' +
                ", assurance='" + assurance + '\'' +
                ", oilWear='" + oilWear + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                '}';
    }
}
