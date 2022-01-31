package com.finki.mojmentor.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;
    private String surname;
    private String username;
    private String password;
    private String birthDate;
    private String email;
    private String gender;
    private float rating;
    private int reviews;
    private String imgUrl = "https://www.placehold.it/200x200";

    @Column(length = 4000)
    private String summary;



    public Student(Long id, String name, String surname, String username, String password, String birthDate, String email, String gender, float rating, int reviews, String summary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.rating = rating;
        this.reviews = reviews;
        this.summary = summary;
    }
}
