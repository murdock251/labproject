package com.example.ver4.models;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String message){
        super(message);
    }
}