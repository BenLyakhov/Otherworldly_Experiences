package com.example.vacations.database;

import androidx.room.Database;

import com.example.vacations.entities.Excursion;
import com.example.vacations.entities.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version=1, exportSchema = false)
public class VacationsDatabaseBuilder {
}
