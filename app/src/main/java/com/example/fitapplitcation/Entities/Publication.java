package com.example.fitapplitcation.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Publication implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int pubId;


    public String UserName ;
    public String Content ;



    public Publication(){

    }
    public Publication(int pubId,String userName,  String content ) {
        this.pubId = pubId;
       this.UserName= userName ;
        this.Content = content;


    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }



}
