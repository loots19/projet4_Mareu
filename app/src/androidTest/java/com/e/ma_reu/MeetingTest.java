package com.e.ma_reu;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.service.DummyMeetingGenerator;
import com.e.ma_reu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MeetingTest {

    // this is fixed
    private static int POSITION_ITEM = 0;

    private MainActivity mActivity;
    private List<Meeting> mMeetingList = DummyMeetingGenerator.DUMMY_MEETINGS;


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasChildCount(4)));

    }
    @Test
    public void deleteMeeting(){
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasChildCount(4)));
        onView(withId(R.id.activity_main_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,new DeleteViewAction()));
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasChildCount(3)));
    }




}