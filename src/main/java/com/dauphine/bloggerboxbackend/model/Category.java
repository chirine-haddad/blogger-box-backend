package com.dauphine.bloggerboxbackend.model;
import java.util.UUID;

public class Category {
    private UUID id;
    private String name;

    // Constructors, getters, and setters
    public Category() {}

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}