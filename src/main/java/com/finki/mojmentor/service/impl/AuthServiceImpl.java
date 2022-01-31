package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.InvalidArgumentsException;
import com.finki.mojmentor.Model.exceptions.InvalidUserCredentialsException;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final MentorRepository userRepository;

    public AuthServiceImpl(MentorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws InvalidArgumentsException {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
