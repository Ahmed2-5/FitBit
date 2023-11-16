package com.example.fitapplitcation.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitapplitcation.Dao.PublicationDao;
import com.example.fitapplitcation.Dao.UserDAO;
import com.example.fitapplitcation.Entities.Publication;
import com.example.fitapplitcation.Entities.User;


@Database(entities = {Publication.class, User.class}, version=3 , exportSchema = false)
public abstract class PubDataBase extends RoomDatabase {
    public abstract PublicationDao getDao();
    public abstract UserDAO userDao();



    public static PubDataBase INSTANCE ;


    public static PubDataBase getInstance(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context , PubDataBase.class,"PubDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
      return  INSTANCE;

    }





}
