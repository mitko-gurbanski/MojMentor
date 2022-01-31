package com.finki.mojmentor.Model.exceptions;

public class RoomConferenceNotFoundException extends RuntimeException{
    public RoomConferenceNotFoundException(String user1, String user2){
        super(String.format("Room conference not found for %s and %s", user1,user2));
    }
}
