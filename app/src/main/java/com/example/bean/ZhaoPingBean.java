package com.example.bean;

import java.io.Serializable;

public class ZhaoPingBean implements Serializable {
    private int id;
    private String userId;
    private String image;
    private String username;
    private String phone;
    private String address;
    private String ask;
    private String askName;
    private String price;
    private String clearance;

    public ZhaoPingBean(int id, String userId, String image, String username, String phone, String address, String ask, String askName, String price, String clearance) {
        this.id = id;
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.ask = ask;
        this.askName = askName;
        this.price = price;
        this.clearance = clearance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAskName() {
        return askName;
    }

    public void setAskName(String askName) {
        this.askName = askName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }
}
