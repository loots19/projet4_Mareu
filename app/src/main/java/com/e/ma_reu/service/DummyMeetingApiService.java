package com.e.ma_reu.service;

import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.model.Room;

import java.util.ArrayList;
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
    public List<Meeting> getFilterMeetingList(String text) {
        ArrayList<Meeting> meetingsSorted = new ArrayList<>();
        for(Meeting m : mMeetings){
            if(m.getDate().equalsIgnoreCase(text)|| m.getNameRoom().equalsIgnoreCase(text)){
             meetingsSorted.add(m);
            }
        }

        return meetingsSorted;
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

