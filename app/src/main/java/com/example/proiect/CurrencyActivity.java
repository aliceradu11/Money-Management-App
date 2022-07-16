package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrencyActivity extends AppCompatActivity {

    private TextView tvDate;
    EditText etEUR, etUSD, etGBP, etRUB, etJPY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        tvDate = findViewById(R.id.tvDate);
        etEUR = findViewById(R.id.editTextEUR);
        etUSD = findViewById(R.id.editTextUSD);
        etGBP = findViewById(R.id.editTextGBP);
        etRUB = findViewById(R.id.editTextRUB);
        etJPY = findViewById(R.id.editTextJPY);

        Button showBtn = findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network network = new Network(){
                    @Override
                    protected void onPostExecute(InputStream inputStream) {

                        tvDate.setText(currencies.getDate());
                        etEUR.setText(currencies.getEuro());
                        etUSD.setText(currencies.getUsd());
                        etGBP.setText(currencies.getGbp());
                        etRUB.setText(currencies.getRub());
                        etJPY.setText(currencies.getJpy());
                    }
                };

                try {
                    network.execute(new URL("https://www.bnr.ro/nbrfxrates.xml"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //create fxRate rate obj
            Currencies currencies= new Currencies(tvDate.getText().toString(),
                    etEUR.getText().toString(),
                    etUSD.getText().toString(),
                    etGBP.getText().toString(),
                    etRUB.getText().toString(),
                    etJPY.getText().toString());

            try {
                //save in internal file
                writeFxRateToFile("file.dat", currencies);
                currencies = null;
                currencies= readFxRateFromFile("file.dat");
                Toast.makeText(getApplicationContext(), currencies.toString(), Toast.LENGTH_LONG).show();

                //save to Room local database
                CurrenciesDB database = CurrenciesDB.getInstance(getApplicationContext());
                database.getCurrenciesDao().insert(currencies);
                Toast.makeText(getApplicationContext(), "Record successfully inserted!", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

    //writing FXRate to file
    private void writeFxRateToFile(String fileName, Currencies currencies) throws IOException
    {
        FileOutputStream fileOutputStream = openFileOutput(fileName, Activity.MODE_PRIVATE);
        DataOutputStream dos = new DataOutputStream(fileOutputStream);
        dos.writeUTF(currencies.getDate());
        dos.writeUTF(currencies.getEuro());
        dos.writeUTF(currencies.getUsd());
        dos.writeUTF(currencies.getGbp());
        dos.writeUTF(currencies.getRub());
        dos.writeUTF(currencies.getJpy());
        dos.flush();
        fileOutputStream.close();
    }

    //reading FXRate from file
    private Currencies readFxRateFromFile(String fileName) throws IOException
    {
        FileInputStream fileInputStream = openFileInput(fileName);
        DataInputStream dis = new DataInputStream(fileInputStream);
        String date = dis.readUTF();
        String euro = dis.readUTF();
        String usd = dis.readUTF();
        String gbp = dis.readUTF();
        String rub = dis.readUTF();
        String jpy = dis.readUTF();
        Currencies currencies = new Currencies(date, euro, usd, gbp, rub, jpy);
        fileInputStream.close();
        return currencies;
    }
}