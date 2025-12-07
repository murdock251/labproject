package com.example.ver2.models;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String message){
        super(message);
    }
}
