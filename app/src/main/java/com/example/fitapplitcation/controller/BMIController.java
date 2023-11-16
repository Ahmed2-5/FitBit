package com.example.fitapplitcation.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.fitapplitcation.BMIResultActivity;
import com.example.fitapplitcation.Dao.UserRepository;
import com.example.fitapplitcation.R;


/**
 * Controller class managing BMI calculation feature interactions and actions.
 */
public class BMIController {
    private Context context;

    public BMIController(Context newContext) {
        context = newContext;
    }

    private String selectedGender = "";
    private String selectedHeight = "";
    private String selectedWeight = "";
    private String selectedAge = "";

    /**
     * Initiates the BMI calculation and navigates to the result screen.
     *
     * @param activity The current activity.
     */
    public void calculateBMI(AppCompatActivity activity) {
        if (selectedGender.isEmpty() || selectedHeight.isEmpty() || selectedWeight.isEmpty() || selectedAge.isEmpty()) {
            Toast.makeText(context, "Complete all fields for BMI calculation.", Toast.LENGTH_SHORT).show();
            return;
        }

        float height = Integer.parseInt(selectedHeight) / 100.0f;
        float weight = Integer.parseInt(selectedWeight);
        float bmi = weight / (height * height);

        UserRepository userRepository = new UserRepository(context);
        userRepository.insertUserData(selectedGender, selectedHeight, selectedWeight, selectedAge, bmi);

        SharedPreferences sharedPreferences = context.getSharedPreferences("BMI_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSaved", true);
        editor.apply();

        Intent intent = new Intent(context, BMIResultActivity.class);
        intent.putExtra("gender", selectedGender);
        intent.putExtra("height", selectedHeight);
        intent.putExtra("weight", selectedWeight);
        intent.putExtra("age", selectedAge);
        intent.putExtra("bmi", bmi);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * Handles the selection of the male option.
     *
     * @param maleLayout   The layout representing the male option.
     * @param femaleLayout The layout representing the female option.
     * @param maleImage    The image representing the male.
     * @param femaleImage  The image representing the female.
     */
    public void onMaleSelected(RelativeLayout maleLayout, RelativeLayout femaleLayout, ImageView maleImage, ImageView femaleImage) {
        maleLayout.setOnClickListener(view -> {
            selectedGender = "1"; // Male
            maleLayout.setSelected(true);
            femaleLayout.setSelected(false);
            maleImage.setColorFilter(ContextCompat.getColor(context, R.color.green));
            femaleImage.setColorFilter(ContextCompat.getColor(context, R.color.white));
        });
    }

    /**
     * Handles the selection of the female option.
     *
     * @param maleLayout   The layout representing the male option.
     * @param femaleLayout The layout representing the female option.
     * @param maleImage    The image representing the male.
     * @param femaleImage  The image representing the female.
     */
    public void onFemaleSelected(RelativeLayout maleLayout, RelativeLayout femaleLayout, ImageView maleImage, ImageView femaleImage) {
        femaleLayout.setOnClickListener(view -> {
            selectedGender = "0"; // Female
            femaleLayout.setSelected(true);
            maleLayout.setSelected(false);
            femaleImage.setColorFilter(ContextCompat.getColor(context, R.color.green));
            maleImage.setColorFilter(ContextCompat.getColor(context, R.color.white));
        });
    }

    /**
     * Increments the age and updates the TextView accordingly.
     *
     * @param currentAgeTextView The TextView representing the current age.
     */
    public void incrementAge(TextView currentAgeTextView) {
        int age = Integer.parseInt(currentAgeTextView.getText().toString());
        if (age < 120) {
            age++;
            currentAgeTextView.setText(String.valueOf(age));
            selectedAge = String.valueOf(age);
        }
    }

    /**
     * Decrements the age and updates the TextView accordingly.
     *
     * @param currentAgeTextView The TextView representing the current age.
     */
    public void decrementAge(TextView currentAgeTextView) {
        int age = Integer.parseInt(currentAgeTextView.getText().toString());
        if (age > 0) {
            age--;
            currentAgeTextView.setText(String.valueOf(age));
            selectedAge = String.valueOf(age);
        }
    }

    /**
     * Increments the weight and updates the TextView accordingly.
     *
     * @param currentWeightTextView The TextView representing the current weight.
     */
    public void incrementWeight(TextView currentWeightTextView) {
        int weight = Integer.parseInt(currentWeightTextView.getText().toString());
        if (weight < 300) {
            weight++;
            currentWeightTextView.setText(String.valueOf(weight));
            selectedWeight = String.valueOf(weight);
        }
    }

    /**
     * Decrements the weight and updates the TextView accordingly.
     *
     * @param currentWeightTextView The TextView representing the current weight.
     */
    public void decrementWeight(TextView currentWeightTextView) {
        int weight = Integer.parseInt(currentWeightTextView.getText().toString());
        if (weight > 0) {
            weight--;
            currentWeightTextView.setText(String.valueOf(weight));
            selectedWeight = String.valueOf(weight);
        }
    }

    /**
     * Sets the height using the SeekBar and updates the TextView.
     *
     * @param heightSeekBar        The SeekBar representing the height.
     * @param currentHeightTextView The TextView representing the current height.
     */
    public void setHeight(SeekBar heightSeekBar, TextView currentHeightTextView) {
        heightSeekBar.setMax(300);

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedHeight = String.valueOf(progress);
                currentHeightTextView.setText(selectedHeight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
