package com.example.inventarymanagementsystem.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.inventarymanagementsystem.room.converters.DateConverter;
import com.example.inventarymanagementsystem.room.dao.CatagoryDao;
import com.example.inventarymanagementsystem.room.dao.HistoryDao;
import com.example.inventarymanagementsystem.room.dao.UserDao;
import com.example.inventarymanagementsystem.room.entities.Catagory;
import com.example.inventarymanagementsystem.room.entities.User;
import com.example.inventarymanagementsystem.room.entities.UserHistory;

@Database(entities = {User.class, Catagory.class, UserHistory.class},exportSchema = false,version = 1)
@TypeConverters({DateConverter.class})
public abstract class InventoryDatabase extends RoomDatabase {
    private static final String DB_NAME = "Inventory_DB";
    private static InventoryDatabase instance;

    public static synchronized InventoryDatabase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),InventoryDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }

    public abstract UserDao userDao();
    public abstract CatagoryDao catagoryDao();
    public abstract HistoryDao userHistoryDao();

}
