package com.example.fitapplitcation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
   // FirebaseAuth auth;
    Button button,profileButton,userListBtn,publicationbtn,bmibtn;

    TextView textView;
   // FirebaseUser user;
    private int totalSteps = 0; // Total step count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileButton= findViewById(R.id.profile);
    //    auth= FirebaseAuth.getInstance();
        button= findViewById(R.id.logout);
        userListBtn=findViewById(R.id.userList);
        publicationbtn= findViewById(R.id.publications);
        bmibtn = findViewById(R.id.bmi);
        //user = auth.getCurrentUser();
        SharedPreferences preferences = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        String storedEmail = preferences.getString("email", "");
        String storedPassword = preferences.getString("password", "");

        if (TextUtils.isEmpty(storedEmail) || TextUtils.isEmpty(storedPassword)) {
            // No stored login credentials, redirect to LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  FirebaseAuth.getInstance().signOut();
               clearLoginCreds();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                startActivity(intent);
                finish();
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        userListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        publicationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),frontPublication.class);
                startActivity(intent);
                finish();
            }
        });
        bmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BMIActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize sensor and manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Button to show steps
        Button showStepsButton = findViewById(R.id.ShowStepCounter);
        showStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StepCounterActivity.class);
                intent.putExtra("total_steps", totalSteps);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = (int) event.values[0];
        }
    }
    private void clearLoginCreds() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for step counter sensor
    }
}