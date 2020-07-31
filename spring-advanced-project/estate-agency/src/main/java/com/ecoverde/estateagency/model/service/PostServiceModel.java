package com.ecoverde.estateagency.model.service;

import com.ecoverde.estateagency.model.entity.User;

import java.time.LocalDateTime;

public class PostServiceModel extends BaseServiceModel{
    private UserServiceModel author;
    private String title;
    private LocalDateTime createdAt;
    private String content;

    public PostServiceModel() {
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
