package com.example.fitapplitcation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class StepCounterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        // Retrieve the total_steps value from the intent
        int totalSteps = getIntent().getIntExtra("total_steps", 0);

        // Find the TextView in the layout to display the step count
        TextView stepCountTextView = findViewById(R.id.stepCountTextView);

        // Set the text of the TextView to display the step count
        stepCountTextView.setText("Total Steps Walked: " + totalSteps);
    }
}
