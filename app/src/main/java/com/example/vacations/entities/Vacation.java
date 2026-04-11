package com.example.vacations.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacations")
public class Vacation {

    @PrimaryKey(autoGenerate = true)
    private int vacationID;
    private String vacationName;
    private double price;

    public int getVacationID() {
        return vacationID;
    }
    public String toString(){return vacationName; } // this line is for the spinner, timestamp 1:40:06 in video 4

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vacation(int vacationID, String vacationName, double price) {
        this.vacationID = vacationID;
        this.vacationName = vacationName;
        this.price = price;
    }
}
