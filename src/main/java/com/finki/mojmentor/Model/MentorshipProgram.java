package com.finki.mojmentor.Model;


import com.finki.mojmentor.Model.enumerations.MentorshipProgramLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MentorshipProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String programName;
    public String programDescription;
    public float price;

    @Column(nullable = true, length = 400)
    public String img;
    public int totalSales = 0;
    public Double rating = 0.0;
    public int ratingCount = 0;

    @ManyToOne
    public User author = new User();

    @Column(length = 20000)
    public String summary;

    @Enumerated(value = EnumType.STRING)
    private MentorshipProgramLevel level;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Category> categoryList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> studentsEnrolled = new ArrayList<>();


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;


    public Long getId() {
        return id;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public float getPrice() {
        return price;
    }

    public String getImg() {
        if (img.contains("http"))
        {
            return img;
        }
        else
        {
            return "/mentorship-program-photos/" + id + "/" + img;
        }
       /* if (img!=null || img!=""){
            return "/mentorship-program-photos/" + id + "/" + img;
        }
        return "https://www.placehold.it/32x32";*/
    }

    public int getTotalSales() {
        return totalSales;
    }

    public Double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public User getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public MentorshipProgramLevel getLevel() {
        return level;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public List<User> getStudentsEnrolled() {
        return studentsEnrolled;
    }


}
