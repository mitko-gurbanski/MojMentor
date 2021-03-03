package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.enumerations.Role;
import com.finki.mojmentor.Model.exceptions.InvalidUsernameOrPasswordException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Mentor register(String username, String password, String repeatPassword, String firstName, String lastName, Role role) throws InvalidUsernameOrPasswordException;
}
