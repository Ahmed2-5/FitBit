package com.example.fitapplitcation;

import com.example.fitapplitcation.Entities.Publication;

public interface AdapterListener {
    void OnUpdate(Publication publication);
    void OnDelete(int idp , int post) ;
    void onDelete(String email,int position) ;


}
