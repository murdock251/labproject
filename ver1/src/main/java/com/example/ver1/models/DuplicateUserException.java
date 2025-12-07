package com.example.ver1.models;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message)
    {
        super(message);
    }
}
