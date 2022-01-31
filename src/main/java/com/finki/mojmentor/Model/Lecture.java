package com.finki.mojmentor.Model;

import com.finki.mojmentor.Model.enumerations.LectureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lecture{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private LectureType lectureType = LectureType.PAID;

    private Long duration;

}
