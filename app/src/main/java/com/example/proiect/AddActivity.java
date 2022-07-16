package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    public static final String ADD_ITEM = "addItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();

        Spinner spinner = findViewById(R.id.spinnerItemCategory);
        String[] typeArray = {"Health", "Groceries", "House", "Entertainment", "Eating out", "Clothes", "Gifts", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(),
                R.layout.support_simple_spinner_dropdown_item, typeArray);
        spinner.setAdapter(adapter);
        EditText etAmount = findViewById(R.id.editTextAmount);
        EditText etItemNote = findViewById(R.id.editTextNote);
        EditText etItemDate = findViewById(R.id.editTextDate);

       if (intent.hasExtra(MainActivity.EDIT_ITEM)) {
            Item item = (Item) intent.getSerializableExtra(MainActivity.EDIT_ITEM);

            ArrayAdapter<String> adapter1 = (ArrayAdapter<String>) spinner.getAdapter();
            for (int i = 0; i < adapter1.getCount(); i++)
                if (adapter1.getItem(i).equals(item.getCategory())) {
                    spinner.setSelection(i);
                    break;
                }
           etAmount.setText(String.valueOf(item.getAmount()));
            etItemNote.setText(item.getNotes());
            etItemDate.setText(new SimpleDateFormat("mm/dd/yyyy", Locale.US).format(item.getDate()));
        }

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (etAmount.getText().toString().isEmpty())
                    etAmount.setError("Please enter the allocated amount!");
                else if (etItemDate.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please fill in the date!", Toast.LENGTH_LONG).show();
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    try {
                        sdf.parse(etItemDate.getText().toString());
                        String category = spinner.getSelectedItem().toString();
                        float amount = Float.parseFloat(etAmount.getText().toString());
                        String notes = etItemNote.getText().toString();
                        Date addingDate = new Date(etItemDate.getText().toString());

                        Item item = new Item(category, amount, notes, addingDate);

                        SharedPreferences spf = getSharedPreferences("itemPrefs", 0);
                        SharedPreferences.Editor editor = spf.edit();
                        editor.putFloat("amount", item.getAmount());
                        editor.putString("category", item.getCategory());
                        editor.putString("notes", item.getNotes());
                        editor.putString("addingDate", item.getDate().toString());
                        editor.apply();

                        intent.putExtra(ADD_ITEM, item);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}