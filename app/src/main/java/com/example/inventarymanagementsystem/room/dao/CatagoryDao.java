package com.example.inventarymanagementsystem.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventarymanagementsystem.room.entities.Catagory;

import java.util.List;

@Dao
public interface CatagoryDao {
    @Query("SELECT * FROM Catagories")
    List<Catagory> getCatagories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCatagory(Catagory catagory);

    @Update
    void updateCatagory(Catagory catagory);

    @Delete
    void DeleteCatagory(Catagory catagory);
}
