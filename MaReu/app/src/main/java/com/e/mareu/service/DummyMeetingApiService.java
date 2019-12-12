package com.e.mareu.service;

import com.e.mareu.model.Meeting;
import com.e.mareu.model.Room;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
    private List<Room> mRooms = DummyRoomGenerator.generateRooms();

    @Override
    public List<Meeting> getMeetingList() {
        return mMeetings;
    }

    @Override
    public List<Room> getRoomList() {
        return mRooms;
    }



    @Override
    public void addMeeting(Meeting meeting) {
        if(!mMeetings.contains(meeting)){
            mMeetings.add(meeting);
        }

    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);

    }

    @Override
    public void addRoom(Room room) {

    }

    @Override
    public void deleteRoom(Room room) {

    }

}
