package com.e.ma_reu.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Meeting;

import static com.e.ma_reu.utils.Utils.makeDialog;
import static com.e.ma_reu.utils.Utils.makeNumberDialog;

public class MyCustomDialogFragment extends DialogFragment {

    private Meeting mMeeting;
    private Button mButtonC;
    private TextView mTextViewDateC;
    private TextView mTextViewTimeC;
    private TextView mTextViewParticipantC;
    private TextView mTextViewSujetC;
    private TextView mTextViewListC;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_layout,container,false);
        mButtonC = view.findViewById(R.id.button_custom);
        mTextViewDateC = view.findViewById(R.id.tv_dateCustom);
        mTextViewTimeC = view.findViewById(R.id.tv_timeCustom);
        mTextViewParticipantC = view.findViewById(R.id.tv_participantCustom);
        mTextViewSujetC = view.findViewById(R.id.tv_subjectCustom);
        mTextViewListC = view.findViewById(R.id.Tv_listCustom);

        mButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

                String  str = makeDialog(mMeeting.getMail());
                String s = makeNumberDialog(mMeeting.getMail());

                mTextViewDateC.setText(mMeeting.getDate());
                mTextViewTimeC.setText(mMeeting.getTime());
                mTextViewParticipantC.setText(str);
                mTextViewSujetC.setText(mMeeting.getSubject());
                mTextViewListC.setText(s);


            }
        });


        return view;
    }


}
