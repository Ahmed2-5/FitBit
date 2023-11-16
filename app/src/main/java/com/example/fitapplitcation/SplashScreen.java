package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imageView = findViewById(R.id.appsplash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }

        },3500);
    }
}