package com.example.proiect;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrenciesDao {

    @Insert
    long insert(Currencies currencies);

    @Query("select * from currencies")
    List<Currencies> getAll();

    @Query("delete from currencies")
    void deleteAll();

    @Delete
    void delete(Currencies currencies);

  /*  @Query("select * from currencies where details=:details ")
    List<Currencies> getCurrenciesByDetails(String details);*/
}
