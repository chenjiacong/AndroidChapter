package com.example.allchaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter10.service.ServiceActivity;
import com.example.chapter12.MaterialActivity;
import com.example.chapter122.MaterialDesignActivity;
import com.example.chapter3.Chapter3Activity;
import com.example.chapter4.Chapter4Activity;
import com.example.chapter6.saveMethod.SaveActivity;
import com.example.chapter6.sqlite.SqliteActivity;
import com.example.chapter8.notification.NotificationActivity;
import com.example.chapter9.http.HttpActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button button3;
    private Button button4;
    private Button button6;

    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);


        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent intent = new Intent(this, Chapter3Activity.class);
                startActivity(intent);
                break;


            case R.id.button4:
                Intent intent2 = new Intent(this, Chapter4Activity.class);
                startActivity(intent2);

                break;

            case R.id.button6:
                Intent intent1 = new Intent(this, SaveActivity.class);
                startActivity(intent1);
                break;
            case R.id.button7:
                Intent intent3 = new Intent(this, SqliteActivity.class);
                startActivity(intent3);
                break;

            case R.id.button8:
                Intent intent4 = new Intent(this, NotificationActivity.class);
                startActivity(intent4);
                break;
            case R.id.button9:
                Intent intent5 = new Intent(this, HttpActivity.class);
                startActivity(intent5);
                break;
            case R.id.button10:
                Intent intent6 = new Intent(this, ServiceActivity.class);
                startActivity(intent6);
                break;
            case R.id.button12:
                Intent intent7=new Intent(this, MaterialDesignActivity.class);
                startActivity(intent7);
                break;
        }
    }

//
}