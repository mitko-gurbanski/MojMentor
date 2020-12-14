package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MentorRepository {

    public List<Mentor> listAllMentors(){
        return DataHolder.mentorList;
    }
}
