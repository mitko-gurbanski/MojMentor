package com.finki.mojmentor.Model;

import com.finki.mojmentor.Model.enumerations.NotificationStatus;
import com.finki.mojmentor.Model.enumerations.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    private String transacationID;

    @ManyToOne(cascade = CascadeType.ALL)
    private User buyer;

    @ManyToOne(cascade = CascadeType.ALL)
    private MentorshipProgram mentorshipProgram;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;


}
