package com.e.ma_reu.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Meeting;

import static com.e.ma_reu.utils.Utils.getListOfParticipant;
import static com.e.ma_reu.utils.Utils.getNumbersOfParticipant;


public class MyCustomDialogFragment extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private Meeting mMeeting;
    private Button mButtonC;
    private TextView mTextViewDateC;
    private TextView mTextViewTimeC;
    private TextView mTextViewParticipantC;
    private TextView mTextViewSubjectC;
    private TextView mTextViewListC;

    static MyCustomDialogFragment newInstance(Meeting meeting) {
        MyCustomDialogFragment f = new MyCustomDialogFragment();

        Bundle args = new Bundle();
        args.putParcelable("meeting", meeting);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_layout, container, true);
        mButtonC = view.findViewById(R.id.button_custom);
        mTextViewDateC = view.findViewById(R.id.tv_dateCustom);
        mTextViewTimeC = view.findViewById(R.id.tv_timeCustom);
        mTextViewParticipantC = view.findViewById(R.id.tv_participantCustom);
        mTextViewSubjectC = view.findViewById(R.id.tv_subjectCustom);
        mTextViewListC = view.findViewById(R.id.Tv_listCustom);

        mButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

            }
        });

        mMeeting = getArguments().getParcelable("meeting");

        String str = getListOfParticipant(mMeeting.getMail());
        String s = getNumbersOfParticipant(mMeeting.getMail());

        mTextViewDateC.setText(mMeeting.getDate());
        mTextViewTimeC.setText(mMeeting.getTime());
        mTextViewParticipantC.setText(s);
        mTextViewSubjectC.setText(mMeeting.getSubject());
        mTextViewListC.setText(str);

        return view;

    }


}

