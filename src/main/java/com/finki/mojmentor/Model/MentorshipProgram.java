package com.finki.mojmentor.Model;

import lombok.Data;

@Data
public class MentorshipProgram {
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
