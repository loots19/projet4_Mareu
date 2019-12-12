package com.e.mareu.adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.mareu.R;
import com.e.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.meetingViewHolder> {

    private List<Meeting> mMeetingList;

    public RecyclerViewAdapter(List<Meeting> items) {
        mMeetingList = items;

    }

    @Override
    public meetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list, parent, false);
        return new meetingViewHolder(view);

    }

    public class meetingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_avatar)
        ImageView mImageViewAvatar;
        @BindView(R.id.item_roomName)
        TextView mTextViewRoom;
        @BindView(R.id.item_subject)
        TextView mTextViewSubject;
        @BindView(R.id.item_time)
        TextView mTextViewTime;
        @BindView(R.id.item_date)
        TextView mTextViewDate;
        @BindView(R.id.item_mail)
        TextView mTextViewMail;
        @BindView(R.id.item_deleteButton)
        ImageButton mButtonDelete;


        public meetingViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }

            });

        }


        public void showDialog() {
            String str = mTextViewMail.getText().toString();
            String[] tokensVal = str.split(";");
            List<String> container = Arrays.asList(tokensVal);
            String listText = "" + container.size();
            String participants = "";
            for (String s : container) {
                participants += s + "\n";
            }
            final Dialog dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.custom_layout);
            Button dialogButton = dialog.findViewById(R.id.butoon_custom);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            TextView textView = dialog.findViewById(R.id.tv_dateCustom);
            textView.setText(mTextViewDate.getText().toString());
            TextView textView1 = dialog.findViewById(R.id.tv_timeCustom);
            textView1.setText(mTextViewTime.getText().toString());
            TextView textView2 = dialog.findViewById(R.id.tv_participantCustom);
            textView2.setText(listText);
            TextView textView3 = dialog.findViewById(R.id.tv_subjectCustom);
            textView3.setText(mTextViewSubject.getText().toString());
            TextView textView4 = dialog.findViewById(R.id.Tv_listCustom);
            textView4.setText(participants);

            dialog.show();
        }
    }


    /**
     * add a meeting and udapte the list
     *
     * @param newList
     */
    public void udapteList(List<Meeting> newList) {
        mMeetingList = new ArrayList<>();
        mMeetingList.addAll(newList);
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(final meetingViewHolder holder, final int position) {
        final Meeting meeting = mMeetingList.get(position);
        holder.mImageViewAvatar.setImageResource(meeting.getDraw());
        holder.mTextViewRoom.setText(meeting.getNameRoom());
        holder.mTextViewSubject.setText(meeting.getSubject());
        holder.mTextViewTime.setText(meeting.getTime());
        holder.mTextViewDate.setText(meeting.getDate());
        holder.mTextViewMail.setText(meeting.getMail().replaceAll("[\\s , . : / ]", ";"));
        holder.mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMeeting(meeting);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();

    }

    /**
     * remove the meeting from the list and udapte RecyclerView
     *
     * @param meeting
     */
    public void removeMeeting(Meeting meeting) {
        int currPosition = mMeetingList.indexOf(meeting);
        mMeetingList.remove(currPosition);
        notifyItemRemoved(currPosition);
    }
}