package com.diamond.diamond.controllers.user;

public class SampleMessage {
    private String data;
    private Long number;

    public SampleMessage() {

    }

    public SampleMessage(String data, Long number) {
        this.data = data;
        this.number = number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
