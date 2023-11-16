package com.example.fitapplitcation.controller;

import android.content.Context;
import android.graphics.Color;

import com.example.fitapplitcation.Dao.UserRepository;
import com.example.fitapplitcation.Entities.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;
import java.util.List;

public class ChartController {

    public static void populateBarChart(Context context, BarChart chart) {
        new Thread(() -> {
            UserRepository userRepository = new UserRepository(context);
            List<User> users = userRepository.getAllUserData();

            BarDataSet barDataSet = getBarDataSet(users);

            // Update the UI on the main thread
            chart.post(() -> {
                BarData barData = new BarData(barDataSet);
                chart.setData(barData);
                chart.getDescription().setText("Bar Chart for Weight");
                chart.invalidate(); // Refresh the chart
            });
        }).start();
    }

    private static BarDataSet getBarDataSet(List<User> users) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            float weight = Float.parseFloat(user.getWeight());
            entries.add(new BarEntry(i, weight));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Weight");
        dataSet.setColor(Color.GREEN); // Set bar color
        dataSet.setValueTextColor(Color.BLACK); // Set text color

        return dataSet;
    }
}