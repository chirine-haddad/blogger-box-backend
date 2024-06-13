package com.dauphine.bloggerboxbackend.exceptions;

import java.util.UUID;

public class PostNotFoundByIdException extends Exception{
    public PostNotFoundByIdException (UUID postId) {
        super("The post with id " + postId + " was not found.");
    }
}