package com.scnu.lotterysystem.entity;

/* *
* 建立一个信息类，用于返回数据
* */
public class Message {

    private boolean variable;

    private String message;

    public Message() {
        super();
    }
    public Message(boolean variable,String message) {
        this.variable=variable;
        this.message=message;
    }


    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
