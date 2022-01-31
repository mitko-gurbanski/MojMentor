package com.finki.mojmentor.Model.exceptions;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(Long id) {
        super(String.format("User with id "+id+" not found"));
    }

}
