package com.example.ver1.models;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String message){
        super(message);
    }
}
