package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.Role;
import com.finki.mojmentor.Model.exceptions.InvalidUsernameOrPasswordException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String email, String firstName, String lastName, Role role) throws InvalidUsernameOrPasswordException;
}
