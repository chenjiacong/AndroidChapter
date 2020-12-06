package com.example.chapter3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter3.ListView.ListView2Activity;
import com.example.chapter3.ListView.ListViewActivity;
import com.example.chapter3.RecycleView.RecycleViewActivity;
import com.example.chapter3.RecycleView.RecyclerView2Activity;
import com.example.chapter3.Summary.MsgActivity;


public class Chapter3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chapter3);
        initView();

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            Intent intent = new Intent(this, ListViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.button2) {
            Intent intent = new Intent(this, ListView2Activity.class);
            startActivity(intent);
        } else if (id == R.id.button3) {
            Intent intent = new Intent(this, RecycleViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.button4) {
            Intent intent = new Intent(this, RecyclerView2Activity.class);
            startActivity(intent);
        } else if (id == R.id.button5) {
            Intent intent = new Intent(this, MsgActivity.class);
            startActivity(intent);
        }
    }
}