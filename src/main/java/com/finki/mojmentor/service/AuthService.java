package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.InvalidArgumentsException;

public interface AuthService {
    User login(String username, String password) throws InvalidArgumentsException;
}
