package com.example.inventarymanagementsystem.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventarymanagementsystem.room.entities.UserHistory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM History WHERE UserPhnoNo=:userPhno ORDER BY Date DESC")
    Observable<List<UserHistory>> getUserHistory(String userPhno);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUserHistory(UserHistory userHistory);

    @Update
    Single<Integer> updateUserHistory(UserHistory userHistory);

    @Delete
    void deleteUserHistory(UserHistory userHistory);

    @Query("DELETE FROM History")
    Completable truncateTable();

}
