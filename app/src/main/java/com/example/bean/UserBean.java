package com.example.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String id;
    private String image;
    private String username;
    private String speciality;
    private String phone;
    private String password;
    private String hetong;
    private String yyzz;
    private String clearance;

    public UserBean(String id, String image, String username, String speciality, String phone, String password, String hetong, String yyzz, String clearance) {
        this.id = id;
        this.image = image;
        this.username = username;
        this.speciality = speciality;
        this.phone = phone;
        this.password = password;
        this.hetong = hetong;
        this.yyzz = yyzz;
        this.clearance = clearance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHetong() {
        return hetong;
    }

    public void setHetong(String hetong) {
        this.hetong = hetong;
    }

    public String getYyzz() {
        return yyzz;
    }

    public void setYyzz(String yyzz) {
        this.yyzz = yyzz;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }
}
