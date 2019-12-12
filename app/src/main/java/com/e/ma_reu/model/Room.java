package com.e.ma_reu.model;

public class Room {

    private String mName;
    private int mDrawRoom;

    public Room(String name, int drawRoom) {
        mName = name;
        mDrawRoom = drawRoom;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getDrawRoom() {
        return mDrawRoom;
    }

    public void setDrawRoom(int drawRoom) {
        mDrawRoom = drawRoom;
    }
}