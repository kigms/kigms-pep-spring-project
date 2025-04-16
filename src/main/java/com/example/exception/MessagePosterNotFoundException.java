package com.example.exception;

public class MessagePosterNotFoundException extends RuntimeException{
    public MessagePosterNotFoundException(){
        super("The account this message is attempting to post from does not exist.");
    }
}
