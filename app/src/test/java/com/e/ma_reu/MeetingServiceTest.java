package com.e.ma_reu;

import com.e.ma_reu.di.DI;
import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.service.DummyMeetingGenerator;
import com.e.ma_reu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MeetingServiceTest {
    private MeetingApiService service;

    @Before
    public void setup(){
        service = DI.getNewInstanceApiService();
    }
    @Test
    public void getMeetingWithSucess(){
        List<Meeting> meetings = service.getMeetingList();
        List<Meeting> expaectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expaectedMeetings.toArray()));
    }
    @Test
    public void deleteMeetingWithSucess(){
        Meeting meetingToDelete = service.getMeetingList().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetingList().contains(meetingToDelete));
    }
    @Test
    public void addMeetingWithSucess(){
        service.getMeetingList().clear();
        Meeting meeting = new Meeting(R.drawable.ic_oval3, "salle 3", "Livraison", "9 : 0", "11/13/2019", "maceo@lamzone;manu@lamzone;philipe@lamzone");
        service.addMeeting(meeting);
        assertEquals(1,service.getMeetingList().size());
    }
    @Test
    public void filterWithSucess(){
        
    }

}