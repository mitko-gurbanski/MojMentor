package com.finki.mojmentor;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.MentorshipProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
@ServletComponentScan
/*@RestController*/
public class MojMentorApplication {

    /*@RequestMapping("/home")*/
    public static String home(){
        /*Mentor mentor = new Mentor("Mitko","Gurbanski","Mitko06","testPassword","03/02/1995","gurbanskimitko@gmail.com");
        mentor.updateMentor("name","Trajko");
        MentorshipProgram newProgram = new MentorshipProgram("Business Development Mentorship","This program contains a lot of modules and will help the user create new business from scratch",650);
        mentor.addProgram(newProgram);
        MentorshipProgram newProgram1 = new MentorshipProgram("Business Development Mentorship","This program contains a lot of modules and will help the user create new business from scratch",650);
        mentor.addProgram(newProgram1);
        return mentor.toString();*/
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(MojMentorApplication.class, args);
    }

}
