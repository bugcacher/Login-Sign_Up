package com.example.roompractice;

import android.app.Application;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
    abstract UserDao userDao();

    public  static synchronized UserDatabase getInstance(Application application)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(application.getApplicationContext()
                    ,UserDatabase.class,"User_Databse")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
