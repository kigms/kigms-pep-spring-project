package com.example.exception;

public class InvalidRegistrationException extends RuntimeException{
    public InvalidRegistrationException(){
        super("Invalid registration input.");
    }
}
