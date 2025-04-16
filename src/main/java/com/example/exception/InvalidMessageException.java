package com.example.exception;

public class InvalidMessageException extends RuntimeException{
    public InvalidMessageException(){
        super("Invalid message. Message is either empty or has exceeded the 255 character limit.");
    }
}
