package com.e.ma_reu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Meeting implements Parcelable {

    private int mDraw;
    private String mNameRoom;
    private String mSubject;
    private String mTime;
    private String mDate;
    private String mMail;


    public Meeting(int draw, String nameRoom, String subject, String time, String date, String mail) {
        mDraw = draw;
        mNameRoom = nameRoom;
        mSubject = subject;
        mTime = time;
        mDate = date;
        mMail = mail;
    }

    protected Meeting(Parcel in) {
        mDraw = in.readInt();
        mNameRoom = in.readString();
        mSubject = in.readString();
        mTime = in.readString();
        mDate = in.readString();
        mMail = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public int getDraw() {
        return mDraw;
    }

    public void setDraw(int draw) {
        mDraw = draw;
    }

    public String getNameRoom() {
        return mNameRoom;
    }

    public void setNameRoom(String nameRoom) {
        mNameRoom = nameRoom;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDraw);
        dest.writeString(mNameRoom);
        dest.writeString(mSubject);
        dest.writeString(mTime);
        dest.writeString(mDate);
        dest.writeString(String.valueOf(mMail));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return  Objects.equals(mNameRoom, meeting.mNameRoom)&&
                Objects.equals(mTime, meeting.mTime)&&
                Objects.equals(mDate, meeting.mDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash( mNameRoom,mTime,mDate);
    }
}
