package com.finki.mojmentor.Model;

public class MentorshipProgram {
    public String programName;
    public String programDescription;
    public int price;

    public MentorshipProgram(String programName, String programDescription, int price) {
        this.programName = programName;
        this.programDescription = programDescription;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MentorshipProgram{" +
                "programName='" + programName + '\'' +
                ", programDescription='" + programDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
