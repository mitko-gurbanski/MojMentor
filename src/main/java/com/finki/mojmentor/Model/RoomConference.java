package com.finki.mojmentor.Model;

import com.finki.mojmentor.Model.enumerations.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomConference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user1;

    @OneToOne
    private User user2;

    private String roomToken;

    @Enumerated(value = EnumType.STRING)
    private RoomStatus status;

}