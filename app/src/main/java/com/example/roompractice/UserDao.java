package com.example.roompractice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("SELECT * FROM USER_TABLE")
    List<User> getAllUsers();
    @Query("SELECT * from user_table where username = :username and password = :password")
    User getUser(String username,String password);



}
