package com.dauphine.bloggerboxbackend.dto;


import java.util.UUID;
import com.dauphine.bloggerboxbackend.model.Category;
public class CreationPostRequest {
    private String title;
    private String content;
    private Category category;

    // Getters and setters
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

    public Category getCategoryId() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }
}
