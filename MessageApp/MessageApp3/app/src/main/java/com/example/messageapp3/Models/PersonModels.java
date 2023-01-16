package com.example.messageapp3.Models;

public class PersonModels {
    String img,name;

    public PersonModels(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public PersonModels() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
