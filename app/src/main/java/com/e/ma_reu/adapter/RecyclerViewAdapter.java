package com.e.ma_reu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.meetingViewHolder> {

    private List<Meeting> mMeetingList;
    AppCompatActivity mContext;


    public RecyclerViewAdapter(List<Meeting> items,AppCompatActivity context) {
        mMeetingList = items;
        mContext = context;

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
        private Meeting mMeeting;



        public meetingViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
                    Fragment prev = mContext.getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    DialogFragment dialogFragment = MyCustomDialogFragment.newInstance(mMeeting);
                    dialogFragment.show(ft, "dialog");

                }

            });

        }
        public void bind(Meeting meeting) {
            mMeeting = meeting;
            mImageViewAvatar.setImageResource(meeting.getDraw());
            mTextViewRoom.setText(meeting.getNameRoom());
            mTextViewSubject.setText(meeting.getSubject());
            mTextViewTime.setText(meeting.getTime());
            mTextViewDate.setText(meeting.getDate());
            mTextViewMail.setText(meeting.getMail().replaceAll("[\\s ,!&$.#|:/]", ";"));
            mButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeMeeting(mMeeting);

                }
            });

        }

    }

    /**
     * add a meeting and update the list
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
        holder.bind(meeting);

    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();

    }

    /**
     * remove the meeting from the list and update RecyclerView
     *
     * @param meeting
     */
    public void removeMeeting(Meeting meeting) {
        int currPosition = mMeetingList.indexOf(meeting);
        mMeetingList.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

}