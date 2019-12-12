package com.e.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.e.mareu.Ui.MainActivity;
import com.e.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class MeetingListServiceTest {

    private static int ITEMS_COUNT = 4;
    private static int POSITION_ITEM = 0;
    private MainActivity mActivity;


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());

    }

    @Test
    public void showMeetingList() {
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasChildCount(4)));
    }

    @Test
    public void clickItem_showAlertDialog() {
        onView(withId(R.id.activity_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, click()));
        onView(withId(R.id.alert_details)).check(matches(isDisplayed()));
    }

    @Test
    public void addMeetingItem() {
        onView(withId(R.id.activity_main_fab)).perform(click());
        onView(withId(R.id.create)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 12, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.tvTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 15));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.eTextSubject)).perform(typeText("livraison"));
        onView(withId(R.id.eTextMail)).perform(typeText("maceo@lamzone;manu@lamzone;philipe@lamzone"));
        onView(withId(R.id.spinner_roomName)).perform(click());
        onView(allOf(withId(R.id.spinner_roomName), withText("salle 3"))).perform(click());
        onView(withId(R.id.Button)).perform(click());
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasMinimumChildCount(5)));

    }
    @Test
    public void deleteMeetingItem() {
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasChildCount(4)));
       onView(withId(R.id.activity_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,new DeleteViewAction()));
        onView(withId(R.id.activity_main_recycler_view)).check(matches(hasMinimumChildCount(3)));


    }
    @Test
    public void checkFilterOk() {
        String text = "salle 5";

        onView(withId(R.id.action_search)).perform(click());








    }
}
