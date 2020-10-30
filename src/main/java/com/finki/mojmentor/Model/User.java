package com.finki.mojmentor.Model;

public class User {
    public String name;
    public String surname;
    public String username;
    public String password;
    public String birthDate;
    public String eMail;

    public User(String name, String surname, String username, String password, String birthDate, String eMail) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.eMail = eMail;
    }
}
