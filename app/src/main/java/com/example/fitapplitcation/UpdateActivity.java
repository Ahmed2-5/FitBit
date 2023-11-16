package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitapplitcation.Dao.PublicationDao;
import com.example.fitapplitcation.Entities.Publication;
import com.example.fitapplitcation.Utils.PubDataBase;

public class UpdateActivity extends AppCompatActivity {

    private EditText userNameEd,contentEd;
    private Button update ;

    private Publication publication ;

    private PubDataBase pubDataBase ;

    private PublicationDao publicationDao ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        pubDataBase=PubDataBase.getInstance(this);
        publicationDao=pubDataBase.getDao();


        userNameEd = findViewById(R.id.userName);
        contentEd = findViewById(R.id.content);
        update=findViewById(R.id.update);


        publication=(Publication) getIntent().getSerializableExtra("model");


        if (publication != null) {
            userNameEd.setText(publication.getUserName());
            contentEd.setText(publication.getContent());

        } else {
            Toast.makeText(this, "Publication data is not available", Toast.LENGTH_SHORT).show();
            finish();
            return;

        }





        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Publication pubModel = new Publication(publication.getPubId(),userNameEd.getText().toString(),contentEd.getText().toString());
                publicationDao.update(pubModel);
                finish();


            }
        });
    }
}