package com.example.inventarymanagementsystem.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventarymanagementsystem.room.entities.Catagory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CatagoryDao {
    @Query("SELECT * FROM Catagories")
    Observable<List<Catagory>> getCatagories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCatagory(Catagory catagory);

    @Update
    Single<Integer> updateCatagory(Catagory catagory);

    @Delete
    void DeleteCatagory(Catagory catagory);
}
