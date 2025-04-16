package com.example.exception;

public class InvalidLoginCredentialsException extends RuntimeException{
    public InvalidLoginCredentialsException(){
        super("Invalid username or password.");
    }
}
