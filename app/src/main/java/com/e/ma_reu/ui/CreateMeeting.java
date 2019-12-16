package com.e.ma_reu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.e.ma_reu.R;
import com.e.ma_reu.adapter.RoomAdapter;
import com.e.ma_reu.di.DI;
import com.e.ma_reu.model.Meeting;
import com.e.ma_reu.model.Room;
import com.e.ma_reu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateMeeting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.tvDate)
    TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @BindView(R.id.tvTime)
    TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    @BindView(R.id.eTextSubject)
    EditText mSubject;
    @BindView(R.id.eTextMail)
    EditText mMail;
    @BindView(R.id.Button)
    Button mButtonCreate;

    private String mFormat, n;
    private int mHour, mMinute, mYear, mMonth, mDay, dr;
    private MeetingApiService mApiService;
    private List<Room> mRoomList;
    private List<Meeting> mMeetingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();
        mRoomList = mApiService.getRoomList();
        mMeetingList = mApiService.getMeetingList();

        onCreateButton();


        Spinner spinner = findViewById(R.id.spinnerRooms);
        // Create an ArrayAdapter using the string array and a default spinner layout
        RoomAdapter roomAdapter =new RoomAdapter(this, (ArrayList<Room>) mRoomList);
        spinner.setAdapter(roomAdapter);
        spinner.setOnItemSelectedListener(this);

        mDisplayDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        mYear = cal.get(Calendar.YEAR);
                        mMonth = cal.get(Calendar.MONTH);
                        mDay = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                CreateMeeting.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, mYear, mMonth, mDay);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                mHour = currentTime.get(Calendar.HOUR_OF_DAY);
                mMinute = currentTime.get(Calendar.MINUTE);
                mHour = mHour + 1;

                selectedTimeFormat(mHour, mMinute);

                TimePickerDialog dialog = new TimePickerDialog(CreateMeeting.this, android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay, minute);
                        String time = (hourOfDay + " : " + minute + " " + mFormat);
                        mDisplayTime.setText(time);
                    }
                }, mHour, mMinute, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

        });

    }

    public void selectedTimeFormat(int hour, int minute) {

        if (mHour == 0) {
            mHour += 12;
            mFormat = "AM";
        } else if (mHour == 12) {
            mFormat = "PM";
        } else if (mHour > 12) {
            mHour -= 12;
            mFormat = "PM";
        } else {
            mFormat = "AM";
        }
        mDisplayTime.setText(new StringBuilder().append(hour).append(" : ").append(minute)
                .append(" ").append(mFormat));
    }

    /**
     * action on image button create
     * and create a new Meeting.
     */
    private void onCreateButton() {
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dr = mRoomList.get(position).getDrawRoom();
        n = mRoomList.get(position).getName();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * create a new object meeting with the input.
     *
     * @return new meeting via parecalable
     */

    private Meeting createMeeting() {
        Meeting meeting = new Meeting(dr, n,
                mSubject.getText().toString(),
                mDisplayTime.getText().toString(),
                mDisplayDate.getText().toString(),
                mMail.getText().toString());
        mMail.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Meeting", meeting);

        if (mSubject.getText().toString().length() == 0 || mMail.getText().length() == 0 || mDisplayTime.getText().toString().length() == 0
                || mDisplayDate.getText().toString().length() ==0 ) {
            Toast.makeText(this, R.string.empty_text_label, Toast.LENGTH_SHORT).show();
        } else if (mMeetingList.contains(meeting)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.room_label)
                    .show();
        } else {
            startActivity(intent);
        }
        return meeting;


    }


}


