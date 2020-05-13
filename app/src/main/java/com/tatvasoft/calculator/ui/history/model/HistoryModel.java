package com.tatvasoft.calculator.ui.history.model;

public class HistoryModel {

    private String expression;
    private String finalAns;
    private int id;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getFinalAns() {
        return finalAns;
    }

    public void setFinalAns(String finalAns) {
        this.finalAns = finalAns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HistoryModel(int id, String expression, String finalAns) {
        this.expression = expression;
        this.id = id;
        this.finalAns = finalAns;
    }
}
