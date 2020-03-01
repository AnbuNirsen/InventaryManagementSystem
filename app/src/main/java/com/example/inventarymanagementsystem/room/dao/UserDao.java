package com.example.inventarymanagementsystem.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventarymanagementsystem.room.entities.User;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface UserDao {
    @Query(("SELECT * FROM User WHERE `PhoneNo` = :phno AND `Password` = :password"))
    Observable<User> getUser(String phno, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

}
