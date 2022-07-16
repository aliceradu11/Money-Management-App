package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 200;
    public static final int REQUEST_CODE_EDIT = 300;
    public static final String EDIT_ITEM = "editItem";
    public int poz;
    List<Item> itemList = new ArrayList<>();
    ListView listView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //edit item cred?? by clicking
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                poz = position;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EDIT_ITEM, itemList.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        //DELETE THE Item BY LONG CLICKING
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                Item item = itemList.get(position);
                CustomAdapter adapter = (CustomAdapter) listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete confirmation")
                        .setMessage("Are you sure you want to delete this item?")
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Deletion canceled!", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                itemList.remove(item);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Deleting " + item.toString(), Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            Item item = (Item) data.getSerializableExtra(AddActivity.ADD_ITEM);
            if(item!=null)
            {
                itemList.add(item);

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.itemlistview, itemList, getLayoutInflater())
                {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        Item item1 = itemList.get(position);
                        TextView tvCategory = view.findViewById(R.id.tvCategory);
                        if(item1.getCategory().contains("Health"))
                            tvCategory.setTextColor(Color.RED);
                        else if(item1.getCategory().contains("Groceries"))
                            tvCategory.setTextColor(Color.YELLOW);
                        else if(item1.getCategory().contains("House"))
                            tvCategory.setTextColor(Color.GREEN);
                        else if(item1.getCategory().contains("Entertainment"))
                            tvCategory.setTextColor(Color.MAGENTA);
                        else if(item1.getCategory().contains("Eating out"))
                            tvCategory.setTextColor(Color.CYAN);
                        else if(item1.getCategory().contains("Clothes"))
                            tvCategory.setTextColor(Color.BLUE);
                        else if(item1.getCategory().contains("Gifts"))
                            tvCategory.setTextColor(Color.rgb(255,69,0));
                        else if(item1.getCategory().contains("Other"))
                            tvCategory.setTextColor(Color.DKGRAY);
                        else
                            tvCategory.setTextColor(Color.LTGRAY);
                        return view;
                    }
                };
                listView.setAdapter(adapter);
            }
        }
        else
            //this is for the edit item in order to update the list view
        if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null)
        {
            Item item = (Item) data.getSerializableExtra(AddActivity.ADD_ITEM);
            if(item!=null)
            {
                itemList.get(poz).setCategory(item.getCategory());
                itemList.get(poz).setAmount(item.getAmount());
                itemList.get(poz).setNotes(item.getNotes());
                itemList.get(poz).setDate(item.getDate());

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.itemlistview, itemList, getLayoutInflater())
                {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        Item item1 = itemList.get(position);
                        TextView tvCategory = view.findViewById(R.id.tvCategory);
                        if(item1.getCategory().contains("Health"))
                            tvCategory.setTextColor(Color.RED);
                        else if(item1.getCategory().contains("Groceries"))
                            tvCategory.setTextColor(Color.YELLOW);
                        else if(item1.getCategory().contains("House"))
                            tvCategory.setTextColor(Color.GREEN);
                        else if(item1.getCategory().contains("Entertainment"))
                            tvCategory.setTextColor(Color.MAGENTA);
                        else if(item1.getCategory().contains("Eating out"))
                            tvCategory.setTextColor(Color.CYAN);
                        else if(item1.getCategory().contains("Clothes"))
                            tvCategory.setTextColor(Color.BLUE);
                        else if(item1.getCategory().contains("Gifts"))
                            tvCategory.setTextColor(Color.rgb(255,69,0));
                        else if(item1.getCategory().contains("Other"))
                            tvCategory.setTextColor(Color.DKGRAY);
                        else
                            tvCategory.setTextColor(Color.LTGRAY);
                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }
        }
    }

    //LINK TO MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    ////MENU OPTIONS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option1:
                Intent intent = new Intent(this, CurrencyActivity.class);
                startActivity(intent);
                break;
            case R.id.option2:
                ExtractItemJSON extractItemJSON = new ExtractItemJSON(){

                    //call the pre execute method
                    ProgressDialog progressDialog;

                    @Override
                    //this is called before doInBackground method
                    protected void onPreExecute() {
                        super.onPreExecute();
                        //instantiate the pre execute method
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                    }

                    @Override
                    //called after doInBackground is finished
                    protected void onPostExecute(String s) {

                        //comment it so we are able to cancel it by hand
                        //progressDialog.cancel(); //see the progress label for less than 1sec

                        itemList.addAll(this.listOfItemsJSON);

                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.itemlistview, itemList, getLayoutInflater())
                        {
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);

                                Item item1 = itemList.get(position);

                                TextView tvCategory = view.findViewById(R.id.tvCategory);
                                if(item1.getCategory().contains("Health"))
                                    tvCategory.setTextColor(Color.RED);
                                else if(item1.getCategory().contains("Groceries"))
                                    tvCategory.setTextColor(Color.YELLOW);
                                else if(item1.getCategory().contains("House"))
                                    tvCategory.setTextColor(Color.GREEN);
                                else if(item1.getCategory().contains("Entertainment"))
                                    tvCategory.setTextColor(Color.MAGENTA);
                                else if(item1.getCategory().contains("Eating out"))
                                    tvCategory.setTextColor(Color.CYAN);
                                else if(item1.getCategory().contains("Clothes"))
                                    tvCategory.setTextColor(Color.BLUE);
                                else if(item1.getCategory().contains("Gifts"))
                                    tvCategory.setTextColor(Color.rgb(255,69,0));
                                else if(item1.getCategory().contains("Other"))
                                    tvCategory.setTextColor(Color.DKGRAY);
                                else
                                    tvCategory.setTextColor(Color.LTGRAY);
                                return view;
                            }
                        };
                        listView.setAdapter(adapter);
                    }
                };
                try {
                    extractItemJSON.execute(new URL("https://pastebin.com/raw/0yVDMmat"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.option3:
                Intent intent1 = new Intent(this, BarChartActivity.class);
                intent1.putExtra("list", (ArrayList)itemList);
                startActivity(intent1);
                break;

        }
        return true;
    }
}