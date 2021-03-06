package com.e.ma_reu;

import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.service.DummyMeetingGenerator;
import com.e.ma_reu.ui.MainActivity;
import com.e.ma_reu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.e.ma_reu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MeetingTest {

    // this is fixed
    private static int POSITION_ITEM = 0;
    private static int ITEMS_COUNT = 4;


    private MainActivity mActivity;



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
        onView(withId(R.id.activity_main_recycler_view))
                .check(matches(hasMinimumChildCount(4)));
    }
    @Test
    public void deleteMeeting(){
        onView(withId(R.id.activity_main_recycler_view)).check(matches(hasMinimumChildCount(4)));
        onView(withId(R.id.activity_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,new DeleteViewAction()));
        onView(withId(R.id.activity_main_recycler_view)).check(matches(hasMinimumChildCount(3)));
    }

    @Test
    public void showDetail(){
        onView(withId(R.id.activity_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,click()));
        onView(withId(R.id.custom_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.button_custom)).perform(click());
        onView(withId(R.id.activity_main_recycler_view)).check(matches(isDisplayed()));

    }
    @Test
    public void addMeeting(){
        onView(withId(R.id.activity_main_fab)).perform(click());
        onView(withId(R.id.tvDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.tvTime)).perform(click());
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.spinnerRooms)).perform(click());
        onData(anything()).atPosition(POSITION_ITEM).perform(click());
        onView(withId(R.id.eTextSubject)).perform(typeText("livraisons"));
        onView(withId(R.id.eTextSubject)).perform(closeSoftKeyboard());
        onView(withId(R.id.eTextMail)).perform(typeText("pierre@lamzone.com,sylvain@lamzone.com,david@lamzone.com;stephane@lamzone.com"));
        onView(withId(R.id.eTextMail)).perform(closeSoftKeyboard());
        onView(withId(R.id.Button)).perform(click());
        onView(withId(R.id.activity_main_recycler_view)).check(withItemCount(ITEMS_COUNT+1));

    }

    @Test
    public void sortMenu(){
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.action_search)).perform(typeText("salle 5"));
        onView(withId(R.id.activity_main_recycler_view)).check(matches(hasMinimumChildCount(2)));
        onView(isAssignableFrom(EditText.class)).perform(clearText());
        onView(withId(R.id.action_search)).perform(typeText("15"));
        onView(withId(R.id.activity_main_recycler_view)).check(matches(hasMinimumChildCount(2)));


















    }




}