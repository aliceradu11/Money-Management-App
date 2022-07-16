//package com.example.proiect;
//
//import androidx.room.Entity;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName="details")
//public class Details {
//
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    private String details;
//
//    public Details(String details)
//    {
//        this.details=details;
//    }
//
//    @Ignore
//    public Details(int id, String details)
//    {
//        this.id=id;
//        this.details=details;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDetails() {
//        return details;
//    }
//
//    public void setDetails(String details) {
//        this.details = details;
//    }
//}
