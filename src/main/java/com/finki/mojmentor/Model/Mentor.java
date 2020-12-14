package com.finki.mojmentor.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mentor{
    public String name;
    public String surname;
    public String username;
    public String password;
    public String birthDate;
    public String email;
    public String gender;
    private float rating;
    private int reviews;
    private int sessions;
    private String location;
    private String imgUrl;
    private String summary;

    private List<MentorshipProgram> mentorshipPrograms;

    public Mentor(String name,
                  String surname,
                  String username,
                  String password,
                  String birthDate,
                  String email,
                  String gender,
                  String location,
                  String imgUrl)
    {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.gender=gender;
        this.location=location;
        this.email = email;
        this.reviews=0;
        this.sessions=0;
        this.location="";
        this.imgUrl=imgUrl;
        this.mentorshipPrograms=new ArrayList<>();
    }
}
