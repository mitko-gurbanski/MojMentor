package com.finki.mojmentor.Model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class MentorshipProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String programName;
    public String programDescription;
    public float price;
    public String img;

    public MentorshipProgram(String programName, String programDescription, int price, String img) {
        this.programName = programName;
        this.programDescription = programDescription;
        this.price = price;
        this.img=img;
    }


}
