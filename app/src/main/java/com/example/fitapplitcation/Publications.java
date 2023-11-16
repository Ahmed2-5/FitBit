package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitapplitcation.Dao.PublicationDao;
import com.example.fitapplitcation.Entities.Publication;
import com.example.fitapplitcation.Utils.PubDataBase;

import java.util.List;


public class Publications extends AppCompatActivity implements AdapterListener{

    EditText userNameEd , contentEd  ;
    Button insertBtn ;
    RecyclerView myRecycler;


    private PubDataBase pubDataBase;

    private PublicationDao publicationDao ;
    private PubAdapter pubAdapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        pubDataBase = (PubDataBase) PubDataBase.getInstance(this);
        publicationDao= pubDataBase.getDao();




        userNameEd = findViewById(R.id.userName);
        contentEd = findViewById(R.id.content);
        insertBtn=findViewById(R.id.insert);
        myRecycler=findViewById(R.id.publicationRecycler);


        pubAdapter = new PubAdapter(this,this);
        myRecycler.setAdapter(pubAdapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));





        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userNameEd.getText().toString();
                String content=contentEd.getText().toString();

                Publication publication=new Publication(0,userName,content);
                pubAdapter.addPublication(publication);
                publicationDao.insert(publication);
                userNameEd.setText("");
                contentEd.setText("");

                Toast.makeText(Publications.this, "Inserted", Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void fetchData(){
        List<Publication> publicationList= publicationDao.getAllPublications();
        for (int i=0 ; i<publicationList.size();i++){
            Publication publication=publicationList.get(i);
            pubAdapter.addPublication(publication);

        }
    }

    @Override
    public void OnUpdate(Publication publication) {

        Intent intent= new Intent(this, UpdateActivity.class);
        intent.putExtra("model",publication);
        startActivity(intent);

    }

    @Override
    public void OnDelete(int idp, int post) {
        publicationDao.delete(idp);
        pubAdapter.removePub(post);

    }

    @Override
    public void onDelete(String email, int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        pubAdapter = new PubAdapter(this, this);
        myRecycler.setAdapter(pubAdapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
    }
}