package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.enumerations.Role;
import com.finki.mojmentor.Model.exceptions.InvalidUsernameOrPasswordException;
import com.finki.mojmentor.Model.exceptions.PasswordsDoNotMatchException;
import com.finki.mojmentor.Model.exceptions.UsernameAlreadyExistsException;
import com.finki.mojmentor.repository.UserRepository;
import com.finki.mojmentor.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findMentorByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public Mentor register(String username, String password, String repeatPassword, String name, String surname, Role userRole) throws RuntimeException {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findMentorByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Mentor user = new Mentor(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }
}
