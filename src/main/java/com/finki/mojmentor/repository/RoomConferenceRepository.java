package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.RoomConference;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomConferenceRepository extends JpaRepository<RoomConference,Long> {
    RoomConference readRoomConferenceByUser1(User mentor1);
    List<RoomConference> readRoomConferencesByStatus(RoomStatus status);
    List<RoomConference> readRoomConferenceByStatusAndUser1(RoomStatus status, User mentor);
    List<RoomConference> readRoomConferenceByStatusAndUser2(RoomStatus status, User mentor);
    Optional<RoomConference> findRoomConferenceByStatusAndUser1AndUser2(RoomStatus status, User mentor1, User mentor2);

}
