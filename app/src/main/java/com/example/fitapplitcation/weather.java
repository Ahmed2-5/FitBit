package com.example.fitapplitcation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class weather extends AppCompatActivity {

    EditText etCity , etCountry ;
    TextView  tvResult ;



    private final String url ="http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "7f46984f824af3ce2915a2d46020f5b2";

    DecimalFormat df = new DecimalFormat("#.##");
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
    }

    public void getWeatherDetails(View view) {
        String tempUrl = "" ;
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if (city.isEmpty()){
            tvResult.setText("City fied can not be empty ! ");
        }else {
            if (!country.equals("")){
                tempUrl = url + "?q=" + city + "," + country + "&aapid=" + appid ;
            }else {
                tempUrl = url + "?q=" + city + "&aapid=" + appid ;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Log.d("response",response) ;
                    String output = "" ;
                    try {
                        JSONObject jsonResponse = new JSONObject(response) ;
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather") ;
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description") ;
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main") ;
                        double temp = jsonObjectMain.getDouble("temp") - 273.15 ;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15 ;
                        float pressure = jsonObjectMain.getInt("pressure") ;
                        int humidity = jsonObjectMain.getInt("humidity") ;
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind") ;
                        String wind = jsonObjectWind.getString("speed" );
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("cluods") ;
                        String clouds = jsonObjectClouds.getString("all") ;
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys") ;
                        String countryName = jsonObjectSys.getString("country") ;
                        String cityName = jsonResponse.getString("name");
                        tvResult.setTextColor(Color.rgb(68,134,199));
                        output += "Current weather of " + cityName + " (" + countryName + ")"
                                + "\n Temp: " + df.format(temp) + " °C"
                                + "\n Feels Like: " + df.format(feelsLike) + " °C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";
                        tvResult.setText(output);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }

        }) ;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }
}