package com.e.ma_reu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.e.ma_reu.R;
import com.e.ma_reu.adapter.RecyclerViewAdapter;
import com.e.ma_reu.di.DI;
import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private MeetingApiService mApiService;
    private List<Meeting> mMeetingList = new ArrayList<>();

    @BindView(R.id.activity_main_fab)
    FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        mMeetingList = mApiService.getMeetingList();

        addMeeting();
        actionOnFabButton();



        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        // on récupère notre recyclerView via son id
        mRecyclerView = findViewById(R.id.activity_main_recycler_view);

        // un recyclerView qui utilise un LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // on donne un adapter a notre recyclerView
        mRecyclerViewAdapter = new RecyclerViewAdapter(mMeetingList,this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        // on sépare chaque ligne de notre liste avec un trait
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }

    /**
     * start new activity when the fab button is clicked
     */
    private void actionOnFabButton() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateMeetingActivity = new Intent(MainActivity.this, CreateMeeting.class);
                startActivity(CreateMeetingActivity);

            }
        });

    }

    /**
     * add a meeting create in CreateMeeting activity
     * get the object via parcelable and udapte the list.
     */
    private void addMeeting() {
        if (getIntent().hasExtra("Meeting")) {
            Meeting meeting = getIntent().getParcelableExtra("Meeting");
            if (meeting != null) {
                mMeetingList.add(meeting);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<Meeting> newList = new ArrayList<>();

        for (Meeting meeting : mMeetingList) {

            if (meeting.getNameRoom().contains(userInput)) {
                newList.add(meeting);

            } else if (meeting.getDate().contains(userInput)) {
                newList.add(meeting);
            }
        }
        mRecyclerViewAdapter.udapteList(newList);
        return true;
    }

}



