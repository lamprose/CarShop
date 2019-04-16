package com.carshop.server.domain;

public class SecurityQuestion {

    private User user;              //用户ID
    private String questionType;    //问题类型
    private String answer;          //答案

    public SecurityQuestion(User user, String questionType, String answer) {
        this.user = user;
        this.questionType = questionType;
        this.answer = answer;
    }

    public SecurityQuestion() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "user=" + user +
                ", questionType='" + questionType + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
