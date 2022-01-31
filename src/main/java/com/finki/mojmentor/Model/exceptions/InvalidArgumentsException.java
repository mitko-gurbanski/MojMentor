package com.finki.mojmentor.Model.exceptions;

public class InvalidArgumentsException extends Throwable {
    public InvalidArgumentsException(){
        super(String.format("Valid Arguments Needed"));
    }
}
