package com.e.mareu.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.room.Room;

public class RoomAdapter extends ArrayAdapter<Room> {
    public RoomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
