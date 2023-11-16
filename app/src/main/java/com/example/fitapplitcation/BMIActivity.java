package com.example.fitapplitcation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapplitcation.controller.BMIController;

/**
 * This class manages the UI and user interactions for the BMI calculation feature.
 */
public class BMIActivity extends AppCompatActivity {

    private BMIController controller;

    // Define UI elements
    private TextView currentHeightTextView;
    private TextView currentWeightTextView;
    private TextView currentAgeTextView;
    private ImageView incrementAgeImageView;
    private ImageView decrementAgeImageView;
    private ImageView incrementWeightImageView;
    private ImageView decrementWeightImageView;
    private SeekBar heightSeekBar;
    private Button calculateBMIButton;
    private RelativeLayout maleRelativeLayout;
    private RelativeLayout femaleRelativeLayout;
    private ImageView maleImageView;
    private ImageView femaleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        // Assign UI elements to variables
        currentAgeTextView = findViewById(R.id.currentage);
        currentWeightTextView = findViewById(R.id.currentweight);
        currentHeightTextView = findViewById(R.id.currentheight);
        incrementAgeImageView = findViewById(R.id.incrementage);
        decrementAgeImageView = findViewById(R.id.decrementage);
        incrementWeightImageView = findViewById(R.id.incremetweight);
        decrementWeightImageView = findViewById(R.id.decrementweight);
        calculateBMIButton = findViewById(R.id.calculatebmi);
        heightSeekBar = findViewById(R.id.seekbarforheight);
        maleRelativeLayout = findViewById(R.id.male);
        femaleRelativeLayout = findViewById(R.id.female);
        maleImageView = findViewById(R.id.male_icon);
        femaleImageView = findViewById(R.id.female_icon);

        // Instantiate the BMI Controller
        controller = new BMIController(getApplicationContext());

        // Setting up event listeners and handling user interactions
        controller.onMaleSelected(maleRelativeLayout, femaleRelativeLayout, maleImageView, femaleImageView);
        controller.onFemaleSelected(maleRelativeLayout, femaleRelativeLayout, maleImageView, femaleImageView);
        incrementAgeImageView.setOnClickListener(view -> controller.incrementAge(currentAgeTextView));
        decrementAgeImageView.setOnClickListener(view -> controller.decrementAge(currentAgeTextView));
        incrementWeightImageView.setOnClickListener(view -> controller.incrementWeight(currentWeightTextView));
        decrementWeightImageView.setOnClickListener(view -> controller.decrementWeight(currentWeightTextView));
        controller.setHeight(heightSeekBar, currentHeightTextView);
        calculateBMIButton.setOnClickListener(view -> controller.calculateBMI(this));
    }
}
