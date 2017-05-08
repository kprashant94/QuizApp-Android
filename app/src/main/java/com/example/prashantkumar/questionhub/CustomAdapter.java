package com.example.prashantkumar.questionhub;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    CustomAdapter(Context context, ArrayList<HashMap<String, String>> itemList){
        super(context, R.layout.custom_row, itemList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        HashMap<String, String> item = getItem(position);
        TextView textViewId = (TextView) customView.findViewById(R.id.id);
        TextView textViewName = (TextView) customView.findViewById(R.id.nameTextV);

        textViewId.setText(item.get(KEY_ID));
        textViewName.setText(item.get(KEY_NAME));

        return customView;
    }
}
