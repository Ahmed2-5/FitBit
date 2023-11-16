 package com.example.fitapplitcation.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitapplitcation.BMIActivity;
import com.example.fitapplitcation.R;

/**
 * The BMIResultController class provides methods to display BMI categories and navigate back to the main BMI screen.
 */
public class BMIResultController {

    /**
     * Displays the BMI category, sets background color, and selects an image based on the provided BMI value.
     *
     * @param bmi         The Body Mass Index value used to determine the category.
     * @param bmiCategory A TextView to display the BMI category.
     * @param background  A RelativeLayout used to set the background color.
     * @param imageView   An ImageView to display an image related to the BMI category.
     */
    public static void displayCategoryAndImage(float bmi, TextView bmiCategory, RelativeLayout background, ImageView imageView) {
        if (bmi < 16) {
            bmiCategory.setText("Severe Thinness");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        } else if (bmi < 17) {
            bmiCategory.setText("Moderate Thinness");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi < 18.5) {
            bmiCategory.setText("Mild Thinness");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi < 25) {
            bmiCategory.setText("Normal");
            background.setBackgroundColor(Color.GREEN);
            imageView.setImageResource(R.drawable.ok);
        } else if (bmi < 30) {
            bmiCategory.setText("Overweight");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        } else if (bmi < 35) {
            bmiCategory.setText("Obese Class I");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        } else {
            bmiCategory.setText("Obese Class II");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }
    }

    /**
     * Navigates back to the main BMI screen (BMIActivity) and finishes the current activity.
     *
     * @param activity The current Activity to manage the navigation.
     */
    public static void navigateToMainScreen(Activity activity) {
        activity.startActivity(new Intent(activity, BMIActivity.class));
        activity.finish();
    }
}
