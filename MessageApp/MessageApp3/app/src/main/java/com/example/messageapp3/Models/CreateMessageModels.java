package com.example.messageapp3.Models;

public class CreateMessageModels {
    String title, message;

    public CreateMessageModels(String title, String desc) {
        this.title = title;
        this.message = desc;
    }

    public CreateMessageModels() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
