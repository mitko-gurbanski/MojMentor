package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.RoomConference;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.RoomStatus;
import com.finki.mojmentor.Model.exceptions.RoomConferenceNotFoundException;
import com.finki.mojmentor.repository.RoomConferenceRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.RoomConferenceService;

import java.util.List;

public class RoomConferenceServiceImpl implements RoomConferenceService {
    private final RoomConferenceRepository roomConferenceRepository;
    private final MentorService mentorService;

    public RoomConferenceServiceImpl(RoomConferenceRepository roomConferenceRepository, MentorService mentorService) {
        this.roomConferenceRepository = roomConferenceRepository;
        this.mentorService = mentorService;
    }

    @Override
    public RoomConference readRoomConferenceByUser1(String mentor) {
        return roomConferenceRepository.readRoomConferenceByUser1(mentorService.findMentorByUsername(mentor));
    }

    @Override
    public List<RoomConference> readRoomConferencesByStatus(RoomStatus status) {
        return roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.ACTIVE);
    }

    @Override
    public List<RoomConference> readRoomConferenceByStatusAndUser1(RoomStatus status, String mentor) {
        return roomConferenceRepository.readRoomConferenceByStatusAndUser1(RoomStatus.ACTIVE,mentorService.findMentorByUsername(mentor));
    }

    @Override
    public List<RoomConference> readRoomConferenceByStatusAndUser2(RoomStatus status, String mentor) {
        return null;
    }

    @Override
    public RoomConference findRoomConferenceForUser1AndUser2(String user1, String user2) {
        User mentor1 = mentorService.findMentorByUsername(user1);
        User mentor2 = mentorService.findMentorByUsername(user2);
        return roomConferenceRepository.findRoomConferenceByStatusAndUser1AndUser2(RoomStatus.ACTIVE, mentor1,mentor2).orElseThrow(()->new RoomConferenceNotFoundException(user1,user2));
    }
}
