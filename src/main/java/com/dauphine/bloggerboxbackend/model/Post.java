package com.dauphine.bloggerboxbackend.model;

import java.util.UUID;
import java.time.LocalDateTime;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private UUID categoryId;

    // Constructors, getters, and setters
    public Post() {}

    public Post(UUID id, String title, String content, LocalDateTime createdDate, UUID categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}