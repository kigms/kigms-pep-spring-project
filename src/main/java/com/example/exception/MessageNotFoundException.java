package com.example.exception;

public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException(){
        super("Message could not be found.");
    }
}
