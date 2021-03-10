package com.finki.mojmentor.Model.exceptions;

public class MentorNotFoundException extends RuntimeException{
    public MentorNotFoundException(String username) {
        super(String.format("Mentor %s not found",username));
    }

}
