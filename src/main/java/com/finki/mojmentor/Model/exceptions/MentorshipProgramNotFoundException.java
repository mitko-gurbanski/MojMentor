package com.finki.mojmentor.Model.exceptions;

public class MentorshipProgramNotFoundException extends RuntimeException{
    public MentorshipProgramNotFoundException(String mentorshipProgram) {
        super(String.format("Mentorship Program %s not found",mentorshipProgram));
    }
}
