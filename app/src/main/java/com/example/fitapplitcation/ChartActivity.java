package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fitapplitcation.controller.ChartController;
import com.github.mikephil.charting.charts.BarChart;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        BarChart chart = findViewById(R.id.chart);
        ChartController.populateBarChart(this, chart);
    }
}
