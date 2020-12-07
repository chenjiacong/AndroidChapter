package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        if (preferences.getString("weather",null)!=null){
            Intent intent=new Intent(this,WeatherActivity.class);
         startActivity(intent);
         finish();
        }
    }
}