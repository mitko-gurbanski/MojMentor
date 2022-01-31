package com.finki.mojmentor.Model.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super(String.format("Token not found"));
    }
}
