package com.e.ma_reu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.e.ma_reu.R;
import com.e.ma_reu.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends ArrayAdapter {

    public RoomAdapter(Context context, ArrayList<Room> arrayList){
        super(context,0,arrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_layout,parent,false);
        }
        ImageView imageViewSpinner = convertView.findViewById(R.id.imageRoom_Spinner);
        TextView textViewSpinner = convertView.findViewById(R.id.textRoom_Spinner);

        Room currentItem = (Room) getItem(position);

        if(currentItem != null){
            imageViewSpinner.setImageResource(currentItem.getDrawRoom());
            textViewSpinner.setText(currentItem.getName());

        }
        return convertView;

    }
}



