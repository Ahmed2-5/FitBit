package com.example.fitapplitcation;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsService extends AsyncTask<String,Void, List<String>>  {




    private static final String API_KEY = "6609ef0bdde94f39a97f7f112254c4a1";
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=6609ef0bdde94f39a97f7f112254c4a1";

    private NewsListener listener ;

    public interface NewsListener {
        void onNewsFetched(List<String> news);
    }

    public NewsService(NewsListener listener){
        this.listener=listener ;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        List<String> newsList = new ArrayList<>();

        try {
            URL url = new URL(NEWS_API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Authorization", "Bearer " + API_KEY);

                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject response = new JSONObject(result.toString());

                if (response.getString("status").equals("ok")) {
                    JSONArray articles = response.getJSONArray("articles");

                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject article = articles.getJSONObject(i);
                        String title = article.getString("title");
                        newsList.add(title);
                    }
                } else {
                    // Handle API error
                    String errorMessage = response.getString("message");
                    newsList.add("Error: " + errorMessage);
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            // Handle exceptions
            newsList.add("Error: " + e.getMessage());
        }
        return newsList;
    }







    @Override
    protected void onPostExecute(List<String> newsList) {
        super.onPostExecute(newsList);
        if (listener != null) {
            listener.onNewsFetched(newsList);
        }




    }

}