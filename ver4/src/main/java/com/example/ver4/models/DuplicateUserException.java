package com.example.ver4.models;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message)
    {
        super(message);
    }
}