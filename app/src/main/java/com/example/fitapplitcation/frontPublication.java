package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class frontPublication extends AppCompatActivity {
Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_publication);

        Button btnAddPublication = findViewById(R.id.btnAddPublication);
        Button btnViewNews = findViewById(R.id.btnViewNews);
        Button btnWeather = findViewById(R.id.btnWeather);
        homeBtn=findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAddPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(frontPublication.this, Publications.class);
                startActivity(intent);
            }
        });


        btnViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(frontPublication.this, news.class);
                startActivity(intent);
            }
        });


        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(frontPublication.this , weather.class);
                startActivity(intent);
            }
        });




    }


    }
