package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class news extends AppCompatActivity implements NewsService.NewsListener{

    private TextView newsTextView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsTextView = findViewById(R.id.newsTextView);

        NewsService newsService = new NewsService(this);
        newsService.execute();
    }

    @Override
    public void onNewsFetched(List<String> news) {
        StringBuilder newsStringBuilder = new StringBuilder();
        for (String article : news) {
            newsStringBuilder.append(article).append("\n\n");
        }
        newsTextView.setText(newsStringBuilder.toString());
    }
}