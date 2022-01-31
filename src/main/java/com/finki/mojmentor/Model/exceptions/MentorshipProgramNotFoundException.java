package com.finki.mojmentor.Model.exceptions;

public class MentorshipProgramNotFoundException extends RuntimeException{
    public MentorshipProgramNotFoundException() {
        super(String.format("Mentorship Program NOT FOUND in the Database"));
    }
}
