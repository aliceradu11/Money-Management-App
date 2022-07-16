package com.example.proiect;

import java.io.Serializable;
import java.util.Date;

public class Item  implements Serializable {

    private String category; //spinner {health, groceries, house, entertainment, eating out, clothes, gifts, other}
    private float amount;
    private String notes;
    private Date addingDate;

    public Item(){}

    public Item(String category, float amount, String notes, Date addingDate) {
        this.category = category;
        this.amount = amount;
        this.notes = notes;
        this.addingDate = addingDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return addingDate;
    }

    public void setDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", addingDate=" + addingDate +
                '}';
    }
}
