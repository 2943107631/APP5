package com.example.bean;

import java.io.Serializable;

public class QiuZhiBean implements Serializable {
    private String userId;
    private String image;
    private String username;
    private String speciality;
    private String qiuzhizhuangtai;
    private String gerenjingli;
    private String ziwopingjia;
    private String price;

    public QiuZhiBean(String userId, String image, String username, String speciality, String qiuzhizhuangtai, String gerenjingli, String ziwopingjia, String price) {
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.speciality = speciality;
        this.qiuzhizhuangtai = qiuzhizhuangtai;
        this.gerenjingli = gerenjingli;
        this.ziwopingjia = ziwopingjia;
        this.price = price;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getQiuzhizhuangtai() {
        return qiuzhizhuangtai;
    }

    public void setQiuzhizhuangtai(String qiuzhizhuangtai) {
        this.qiuzhizhuangtai = qiuzhizhuangtai;
    }

    public String getGerenjingli() {
        return gerenjingli;
    }

    public void setGerenjingli(String gerenjingli) {
        this.gerenjingli = gerenjingli;
    }

    public String getZiwopingjia() {
        return ziwopingjia;
    }

    public void setZiwopingjia(String ziwopingjia) {
        this.ziwopingjia = ziwopingjia;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
