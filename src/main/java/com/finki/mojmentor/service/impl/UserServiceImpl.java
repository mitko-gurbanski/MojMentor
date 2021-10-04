package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.Role;
import com.finki.mojmentor.Model.exceptions.AccountIsNotEnabledException;
import com.finki.mojmentor.Model.exceptions.InvalidUsernameOrPasswordException;
import com.finki.mojmentor.Model.exceptions.PasswordsDoNotMatchException;
import com.finki.mojmentor.Model.exceptions.UsernameAlreadyExistsException;
import com.finki.mojmentor.repository.UserRepository;
import com.finki.mojmentor.service.EmailService;
import com.finki.mojmentor.service.EmailTokenService;
import com.finki.mojmentor.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailTokenService emailTokenService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailTokenService emailTokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailTokenService = emailTokenService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findMentorByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String email, String name, String surname, Role userRole) throws RuntimeException {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty() || email.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findMentorByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User mentor = new User(username,email,passwordEncoder.encode(password),name,surname,userRole);
        //set user account disabled
        mentor.setEnabled(false);
        userRepository.save(mentor);
        //Generate new token key
        emailTokenService.save(mentor.getId());
        emailService.sendAccountActivationMail(mentor.getEmail(),emailTokenService.findByUserId(mentor.getId()).getToken());
        return mentor;
    }
}
