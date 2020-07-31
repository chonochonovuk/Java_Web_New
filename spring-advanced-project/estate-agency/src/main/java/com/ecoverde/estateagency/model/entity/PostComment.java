package com.ecoverde.estateagency.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "postComments")
public class PostComment extends BaseEntity{
    private Post post;
    private User author;
    private LocalDateTime publishedAt;
    private String content;

    public PostComment() {
    }

    @ManyToOne
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "publishedAt",nullable = false)
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Column(name = "content",nullable = false,columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
