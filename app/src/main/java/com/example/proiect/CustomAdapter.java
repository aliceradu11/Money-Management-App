package com.example.proiect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int resource;
    private List<Item> itemList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<Item> list, LayoutInflater layoutInflater) {
        super(context,resource,list);
        this.context = context;
        this.resource = resource;
        this.itemList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Item item = itemList.get(position);

        if (item!=null)
        {
            TextView tv1 = view.findViewById(R.id.tvCategory);
            tv1.setText(item.getCategory());

            TextView tv2 = view.findViewById(R.id.tvAmount);
            tv2.setText(String.valueOf(item.getAmount()));

            TextView tv3 = view.findViewById(R.id.tvNotes);
            tv3.setText(item.getNotes());

            TextView tv4 = view.findViewById(R.id.tvDate);
            tv4.setText(item.getDate().toString());
        }
        return view;
    }
}
