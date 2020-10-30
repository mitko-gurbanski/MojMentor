package com.finki.mojmentor.Model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User{
    List<MentorshipProgram> programs;

    public Mentor(String name, String surname, String username, String password, String birthDate,  String eMail) {
        super(name, surname, username, password, birthDate, eMail);
        programs = new ArrayList<MentorshipProgram>();
    }

    public void addProgram(MentorshipProgram newProgram){
        programs.add(newProgram);
    }

    public void updateMentor(String updateField, String newRecord){
        switch(updateField){
            case "name":
                super.name=newRecord;
                break;
            case "surname":
                super.surname=newRecord;
                break;
            case "username":
                super.username=newRecord;
                break;
            case "password":
                super.password=newRecord;
                break;
            case "birthDate":
                super.birthDate=newRecord;
                break;
        }

    }

    @Override
    public String toString() {
        return "Name: "+super.name + " Surname: "+ super.surname + "Username: " +super.username+ "Password"+ super.password +"Birthday: "+ super.birthDate + programs.toString();
    }
}
