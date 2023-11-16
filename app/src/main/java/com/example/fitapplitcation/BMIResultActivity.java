package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitapplitcation.controller.BMIResultController;


/**
 * The BMIResultActivity class manages the display of BMI results and allows navigation to different weight-related activities.
 */
public class BMIResultActivity extends AppCompatActivity {

    // UI elements
    private TextView bmiDisplay, bmiCategory, gender;
    private Button goToMain,homeBtn;
    private Intent intent;
    private ImageView imageView;
    private String height;
    private String weight;
    private float bmi;
    private float heightValue, weightValue;
    private RelativeLayout background;

    private Button increaseWeight;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        // Initializing UI elements
        intent = getIntent();
        bmiDisplay = findViewById(R.id.bmidisplay);
        bmiCategory = findViewById(R.id.bmicategorydispaly);
        goToMain = findViewById(R.id.gotomain);
        increaseWeight = findViewById(R.id.buttonChart);
        homeBtn=findViewById(R.id.home);

        imageView = findViewById(R.id.imageview);
        gender = findViewById(R.id.genderdisplay);
        background = findViewById(R.id.contentlayout);

        // Listeners for weight change activities
        increaseWeight.setOnClickListener(v -> startActivity(new Intent(BMIResultActivity.this, ChartActivity.class)));

        // Retrieve height and weight from intent
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        // Calculate BMI
        heightValue = Float.parseFloat(height) / 100;
        weightValue = Float.parseFloat(weight);
        bmi = weightValue / (heightValue * heightValue);
        bmiDisplay.setText(String.valueOf(bmi));

        // Display BMI category, related image, and gender
        BMIResultController.displayCategoryAndImage(bmi, bmiCategory, background, imageView);
        gender.setText(intent.getStringExtra("gender"));

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Handling navigation to the main BMI screen
        goToMain.setOnClickListener(v -> {
            // Update shared preferences value in a background thread
            new Thread(() -> {
                SharedPreferences sharedPreferences = getSharedPreferences("BMI_PREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isSaved", false);
                editor.apply();
            }).start();

            BMIResultController.navigateToMainScreen(BMIResultActivity.this);
        });

    }
}
