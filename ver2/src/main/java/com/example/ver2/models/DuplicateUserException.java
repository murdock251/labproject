package com.example.ver2.models;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message)
    {
        super(message);
    }
}
