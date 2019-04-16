package com.carshop.server.domain;

public class Evaluations {

    private Integer evaId;     //评价ID
    private User user;         //用户ID
    private Cars car;          //车辆ID
    private Integer grade;     //评分
    private String evaluate;   //评价内容
    private String evaTime;    //评价时间

    public Evaluations(User user, Cars car, Integer grade, String evaluate, String evaTime) {
        this.user = user;
        this.car = car;
        this.grade = grade;
        this.evaluate = evaluate;
        this.evaTime = evaTime;
    }

    public Evaluations() {
    }

    public Integer getEvaId() {
        return evaId;
    }

    public void setEvaId(Integer evaId) {
        this.evaId = evaId;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaTime() {
        return evaTime;
    }

    public void setEvaTime(String evaTime) {
        this.evaTime = evaTime;
    }

    @Override
    public String toString() {
        return "Evaluations{" +
                "evaId=" + evaId +
                ", user=" + user +
                ", car=" + car +
                ", grade=" + grade +
                ", evaluate='" + evaluate + '\'' +
                ", evaTime='" + evaTime + '\'' +
                '}';
    }
}
