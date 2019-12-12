package com.e.ma_reu.service;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyRoomGenerator {
    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("salle 1", R.drawable.ic_oval),
            new Room("salle 2", R.drawable.ic_oval2),
            new Room("salle 3", R.drawable.ic_oval3),
            new Room("salle 4", R.drawable.ic_oval4),
            new Room("salle 5", R.drawable.ic_oval5),
            new Room("salle 6", R.drawable.ic_oval6),
            new Room("salle 7", R.drawable.ic_oval7),
            new Room("salle 8", R.drawable.ic_oval8),
            new Room("salle 9", R.drawable.ic_oval9),
            new Room("salle 10", R.drawable.ic_oval1)
    );

    static List<Room>generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }
}
