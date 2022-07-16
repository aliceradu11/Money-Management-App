package com.example.proiect;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/*@Entity(tableName = "currencies", foreignKeys = @ForeignKey(entity = Details.class, parentColumns = "id",
childColumns = "idDetails", onDelete = ForeignKey.CASCADE), indices =@Index("idDetails"))*/
@Entity(tableName = "currencies")
public class Currencies {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public int getIdDetails(){return idDetails;}
    public int setIdDetails(int idDetails){ this.idDetails=idDetails;}
    private int idDetails;*/


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private String euro;
    private String usd;
    private String gbp;
    private String rub;
    private String jpy;

    @Ignore
   public Currencies(){}

    public Currencies(String date, String euro, String usd, String gbp, String rub, String jpy) {
        this.date = date;
        this.euro = euro;
        this.usd = usd;
        this.gbp = gbp;
        this.rub = rub;
        this.jpy = jpy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEuro() {
        return euro;
    }

    public void setEuro(String euro) {
        this.euro = euro;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getGbp() {
        return gbp;
    }

    public void setGbp(String gbp) {
        this.gbp = gbp;
    }

    public String getRub() {
        return rub;
    }

    public void setRub(String rub) {
        this.rub = rub;
    }

    public String getJpy() {
        return jpy;
    }

    public void setJpy(String jpy) {
        this.jpy = jpy;
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "date='" + date + '\'' +
                ", euro='" + euro + '\'' +
                ", usd='" + usd + '\'' +
                ", gbp='" + gbp + '\'' +
                ", rub='" + rub + '\'' +
                ", jpy='" + jpy + '\'' +
                '}';
    }
}
