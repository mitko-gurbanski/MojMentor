package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.RoomConference;
import com.finki.mojmentor.Model.enumerations.RoomStatus;

import java.util.List;

public interface RoomConferenceService {
    RoomConference readRoomConferenceByUser1(String mentor);
    List<RoomConference> readRoomConferencesByStatus(RoomStatus status);
    List<RoomConference> readRoomConferenceByStatusAndUser1(RoomStatus status,String mentor);
    List<RoomConference> readRoomConferenceByStatusAndUser2(RoomStatus status,String mentor);
    RoomConference findRoomConferenceForUser1AndUser2(String user1, String user2);
}
