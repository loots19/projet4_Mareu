package com.e.mareu.service;

import com.e.mareu.model.Meeting;
import com.e.mareu.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting>getMeetingList();
    List<Room>getRoomList();


    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void addRoom (Room room);

    void deleteRoom (Room room);



}

