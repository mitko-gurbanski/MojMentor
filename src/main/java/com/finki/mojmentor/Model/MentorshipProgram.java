package com.finki.mojmentor.Model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class MentorshipProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String programName;
    public String programDescription;
    public float price;
    public String img;

    @ManyToMany(cascade=CascadeType.ALL)
    List<Category> categoryList = new ArrayList<>();

    public MentorshipProgram(String programName, String programDescription, int price) {
        this.programName = programName;
        this.programDescription = programDescription;
        this.price = price;
    }

}
