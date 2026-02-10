package com.structor.appdev.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.structor.appdev.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;
    LayoutInflater inflt;
    public CustomAdapter(@NonNull Context context, int resource, ArrayList<User> users) {
        super(context, resource, users);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        inflt = (LayoutInflater.from(context));
        convertView = inflt.inflate(resource,parent,false);

        //Adapter for ListView with Image
        //Fill with appropriate values
        //ImageView image = convertView.findViewById(R.id.);
        //TextView name = convertView.findViewById(R.id.);
        //TextView breed = convertView.findViewById(R.id.);
        //TextView gender = convertView.findViewById(R.id.);


        //image.setImageResource(getItem(position).get());
        //name.setText(getItem(position).get());
        //breed.setText(getItem(position).get());
        //gender.setText(getItem(position).get());

        return convertView;
    }
}
