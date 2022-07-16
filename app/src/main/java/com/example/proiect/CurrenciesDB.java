package com.example.proiect;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Currencies.class}, version = 1, exportSchema = false)
public abstract class CurrenciesDB extends RoomDatabase {

    public static final String DB_NAME = "currencies.db";
    private static CurrenciesDB instance;

    public static CurrenciesDB getInstance(Context context)
    {
        if(instance==null)
            instance = Room.databaseBuilder(context, CurrenciesDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return instance;
    }

    //reference to the interface
    public abstract CurrenciesDao getCurrenciesDao();
}