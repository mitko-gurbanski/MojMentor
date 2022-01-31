package com.finki.mojmentor.Model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super(String.format("Invalid User Credentials Exception"));
    }
}
