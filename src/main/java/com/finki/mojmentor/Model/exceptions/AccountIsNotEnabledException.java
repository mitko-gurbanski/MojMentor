package com.finki.mojmentor.Model.exceptions;

import javax.servlet.http.HttpServletRequest;

public class AccountIsNotEnabledException extends RuntimeException {
    public AccountIsNotEnabledException(HttpServletRequest request){
        super((String.format("Account is not enabled exception")));
    }
}
