package com.e.ma_reu.service;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(R.drawable.ic_oval3, "salle 3", "Livraison", "9 : 0", "13/11/2019", "maceo@lamzone;manu@lamzone;philipe@lamzone"),
            new Meeting(R.drawable.ic_oval4, "salle 4", "Internationale", "10 : 30", "14/11/2019", "greg@lamzone;david@lamzone"),
            new Meeting(R.drawable.ic_oval5, "salle 5", "Internet", "10 : 0", "15/11/2019", "pierre@lamzone;sylvain@lamzone;david@lamzone;stephane@lamzone"),
            new Meeting(R.drawable.ic_oval5, "salle 5", "Transport", "14 : 0", "15/11/2019", "nina@lamzone;greg@lamzone;dennis@lamzone;eric@lamzone")

    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
