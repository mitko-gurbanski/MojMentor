package com.finki.mojmentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EntityScan
public class MojMentorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MojMentorApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder(10);
    }
}
