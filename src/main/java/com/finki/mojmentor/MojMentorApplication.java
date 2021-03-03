package com.finki.mojmentor;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.MentorshipProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
@ServletComponentScan
public class MojMentorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MojMentorApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder(10);
    }
}
