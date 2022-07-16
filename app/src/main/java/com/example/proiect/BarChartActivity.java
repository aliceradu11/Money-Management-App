package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartActivity extends AppCompatActivity {
    ArrayList<Item> list;
    Map<String, Integer> source;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Intent intent = getIntent();

        list = (ArrayList<Item>)intent.getSerializableExtra("list");

        source = getSource(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarView(getApplicationContext(), source));
    }

    private Map<String, Integer> getSource(List<Item> items)
    {
        if(items==null || items.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String, Integer> results = new HashMap<>();
            for(Item item: items)
                if (results.containsKey(item.getCategory()))
                    results.put(item.getCategory(), results.get(item.getCategory()) + 1);
                else
                    results.put(item.getCategory(), 1);

            return results;
        }
    }
}