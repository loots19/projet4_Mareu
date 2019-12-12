package com.e.mareu;

import com.e.mareu.di.DI;
import com.e.mareu.model.Meeting;
import com.e.mareu.service.DummyMeetingGenerator;
import com.e.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess (){
        List<Meeting> meetings = service.getMeetingList();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings,IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));

    }
    @Test
    public void deleteMeetingWithSuccess (){
        Meeting meetingToDelete = service.getMeetingList().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetingList().contains(meetingToDelete));

    }
    @Test
    public void addMeetingWithSuccess (){
        service.getMeetingList().clear();
        Meeting meeting = new Meeting(R.drawable.ic_oval,"salle 1","Finance","9 : 00","11/12/2019","reloots@lamzone, eric@lamzone, david@lamzone");
        service.addMeeting(meeting);
        assertEquals(1,service.getMeetingList().size());

    }
    @Test
    public void FilterWithSuccess(){


    }




}