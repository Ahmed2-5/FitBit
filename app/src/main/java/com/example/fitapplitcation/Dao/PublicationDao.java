package com.example.fitapplitcation.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitapplitcation.Entities.Publication;

import java.util.List;

@Dao
public interface PublicationDao {
    @Insert
    void insert(Publication publication);

    @Update
    void update(Publication publication);

    @Query("delete from Publication where pubId =:pubId")
    void delete(int pubId);

    @Query("select * from publication")
    List<Publication> getAllPublications();

}
