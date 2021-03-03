package com.finki.mojmentor.Model;

import com.finki.mojmentor.Model.enumerations.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
@Entity
public class Mentor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String password;
    private String birthDate;
    private String email;
    private String gender;
    private float rating;
    private int reviews;
    private int sessions;
    private String location;
    private String imgUrl;
    private String summary;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany
    private List<MentorshipProgram> mentorshipPrograms;

    public Mentor(String name,
                  String surname,
                  String username,
                  String password,
                  String birthDate,
                  String email,
                  String gender,
                  String location)
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
        this.mentorshipPrograms=new ArrayList<>();
    }

    public Mentor() {

    }

    public Mentor(String username, String encode, String name, String surname, Role userRole) {
        this.username=username;
        this.password=encode;
        this.name=name;
        this.surname=surname;
        this.role=userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }


    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
