package com.finki.mojmentor.Model;

import com.finki.mojmentor.Model.enumerations.Role;
import lombok.Data;
import org.h2.engine.Domain;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String password;
    private Date birthDate;
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

    @ManyToMany(cascade=CascadeType.ALL)
    private List<MentorshipProgram> mentorshipPrograms;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<MentorshipProgram> favoriteProgramList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public User(String name,
                  String surname,
                  String username,
                  String password,
                  Date birthDate,
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

    public User() {

    }

    public User(String username, String email, String encode, String name, String surname, Role userRole) {
        this.email=email;
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
