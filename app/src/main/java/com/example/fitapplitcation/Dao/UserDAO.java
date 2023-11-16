package com.example.fitapplitcation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


import com.example.fitapplitcation.Entities.User;
@Dao

public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();
    @Query("SELECT * FROM user_table WHERE email = :email")
    User getUserByEmail(String email);
    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(String email, String password);
    @Query("DELETE FROM user_table WHERE email = :email")
    void deleteByEmail(String email);
    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 1")
    User getUserData();

    @Query("SELECT * FROM user_table")
    List<User> getAllUserData();


    /**
     * Deletes all user entries from the 'user_data' table.
     */
    @Query("DELETE FROM user_table")
    void deleteAllUsers();
}
