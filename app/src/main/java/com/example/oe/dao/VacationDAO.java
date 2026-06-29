package com.example.oe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.oe.entities.Vacation;

import java.util.List;

@Dao
public interface VacationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vacation vacation);
    @Update
    void update(Vacation vacation);
    @Delete
    void delete(Vacation vacation);
    @Query("SELECT * FROM VACATIONS ORDER BY vacationID ASC")
    List<Vacation> getAllVacations();

    @Query("SELECT * FROM VACATIONS WHERE vacationID = :id Limit 1")
    Vacation getVacationByID(int id);

    //    adding the following query for the search function in activity_vacation_list
    @Query("SELECT * FROM VACATIONS WHERE :search = '' OR vacationName LIKE '%' || :search || '%' ORDER BY vacationName ASC")
    List<Vacation> getVacationBySearch(String search);

}
