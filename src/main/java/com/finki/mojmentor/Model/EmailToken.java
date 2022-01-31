package com.finki.mojmentor.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.StringTokenizer;

@Data
@Entity
public class EmailToken{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
    private String token = new RandomString(100).nextString();
    @OneToOne
    private User user;
}
