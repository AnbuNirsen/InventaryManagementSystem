package com.example.inventarymanagementsystem.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventarymanagementsystem.room.entities.UserHistory;

import java.util.List;
@Dao
public interface HistoryDao {
    @Query("SELECT * FROM History WHERE UserPhnoNo=:userPhno ORDER BY Date")
    List<UserHistory> getUserHistory(String userPhno);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserHistory(UserHistory userHistory);

    @Update
    void updateUserHistory(UserHistory userHistory);

    @Delete
    void deleteUserHistory(UserHistory userHistory);

}
