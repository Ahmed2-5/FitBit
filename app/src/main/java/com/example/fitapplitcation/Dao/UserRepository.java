package com.example.fitapplitcation.Dao;

import android.content.Context;
import androidx.room.Room;

import com.example.fitapplitcation.Entities.User;
import com.example.fitapplitcation.Utils.PubDataBase;

import java.util.List;

/**
 * The UserRepository class manages the interaction between the database and the application.
 */
public class UserRepository {
    private PubDataBase db;
    private static final String DATABASE_NAME = "user_data_db";

    /**
     * Initializes the repository and database instance.
     *
     * @param context The application context.
     */
    public UserRepository(Context context) {
        db = Room.databaseBuilder(context, PubDataBase.class, DATABASE_NAME).build();
    }

    /**
     * Inserts user-related data into the database.
     *
     * @param gender The gender of the user.
     * @param height The height of the user.
     * @param weight The weight of the user.
     * @param age    The age of the user.
     * @param bmi    The BMI (Body Mass Index) of the user.
     */
    public void insertUserData(String gender, String height, String weight, String age, float bmi) {
        User user = new User();
        user.setGender(gender);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        user.setBmi(bmi);

        new Thread(() -> db.userDao().insert(user)).start();
    }

    /**
     * Retrieves user-related data from the database.
     *
     * @return The UserEntity object containing user data.
     */
    public User getUserData() {
        return db.userDao().getUserData();
    }

    /**
     * Deletes all user-related data from the database.
     */
    public void deleteAllUsers() {
        db.userDao().deleteAllUsers();
    }

    /**
     * Retrieves all user-related data from the database.
     *
     * @return A list of UserEntity objects containing all user data.
     */
    public List<User> getAllUserData() {
        return db.userDao().getAllUserData();
    }
}
