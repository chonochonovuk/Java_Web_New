package com.ecoverde.estateagency.model.service;

import com.ecoverde.estateagency.model.entity.Post;
import com.ecoverde.estateagency.model.entity.User;

import java.time.LocalDateTime;

public class PostCommentServiceModel {
    private Post post;
    private User author;
    private LocalDateTime publishedAt;
    private String content;

    public PostCommentServiceModel() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
