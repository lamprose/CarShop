package com.carshop.server.domain;

public class SecurityQuestion {

    private String id;              //用户ID
    private String questionType;    //问题类型
    private String answer;          //答案

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
