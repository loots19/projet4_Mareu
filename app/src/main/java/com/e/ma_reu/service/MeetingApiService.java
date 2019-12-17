package com.e.ma_reu.service;


import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.model.Room;

import java.util.ArrayList;
import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetingList();
    List<Room>getRoomList();



    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void addRoom (Room room);

    void deleteRoom (Room room);

}

