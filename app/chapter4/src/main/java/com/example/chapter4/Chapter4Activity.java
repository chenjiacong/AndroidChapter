package com.example.chapter4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter4.Summary.NewsActivity;
import com.example.chapter4.demo.DemoActivity;

public class Chapter4Activity extends AppCompatActivity implements View.OnClickListener {

    private Button demo;
    private Button summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4);
        initView();
    }

    private void initView() {
        demo = (Button) findViewById(R.id.demo);
        summary = (Button) findViewById(R.id.summary);

        demo.setOnClickListener(this);
        summary.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.demo) {
            Intent intent=new Intent(this, DemoActivity.class);
            startActivity(intent);
        } else if (id == R.id.summary) {
            Intent intent=new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
    }
}