package com.example.proiect;

import android.os.AsyncTask;
import android.util.Log;

import com.example.proiect.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractItemJSON extends AsyncTask<URL, Void, String> {

    public List<Item> listOfItemsJSON = new ArrayList<>();

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();

            //extract the input stream and transform it in a string
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String result = "";
            while((line = br.readLine())!=null)
                result+= line;

            //parsing JSON--calling the parseJSON method
            parseJSON(result);

            //return it
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //implement parseJSON method
    public void parseJSON(String jsonStr)
    {
        if(jsonStr!=null)
        {
            //i've tried to implement the desired json format but it didn't work
            /*JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr); //define a json obj

                //JSONObject object = jsonObject.getJSONObject("items");
                float amount = Float.parseFloat(jsonObject.getString("amount"));
                Date date = new Date(jsonObject.getString("date"));
                //String category= jsonObject.getString("category");

                JSONObject categoryObject = jsonObject.getJSONObject("category");
                String category=categoryObject.getString("category");
                String notes = categoryObject.getString("notes");

                    //calling the constr from product class
                    Item item= new Item(category, amount, notes, date);
                    listOfItemsJSON.add(item);//adding the products to the JSON list
                } catch (JSONException e) {
                e.printStackTrace();
            }*/
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr); //define a json obj

                JSONArray items = jsonObject.getJSONArray("items");

                for(int i=0;i<items.length();i++) {
                    //get objs based on i index
                    JSONObject object = items.getJSONObject(i);

                    //extract each attribute/key value
                    String category= object.getString("category");
                    float amount = Float.parseFloat(object.getString("amount"));
                    String notes = object.getString("notes");
                    Date date = new Date(object.getString("date"));

                    //calling the constr from product class
                    Item item= new Item(category, amount, notes, date);
                    listOfItemsJSON.add(item);//adding the products to the JSON list
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Log.e("parseJSON", "jsonStr is null!");
    }
}
