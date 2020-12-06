package com.example.chapter3.ListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.chapter3.R;

public class ListViewActivity extends AppCompatActivity {
    private String[] data = {"apple", "peach", "orange", "banana", "pitch", "strawberry", "watermelon", "pear",
            "apple", "peach", "orange", "banana", "pitch", "strawberry", "watermelon", "pear",
            "apple", "peach", "orange", "banana", "pitch", "strawberry", "watermelon", "pear"};
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = data[position];
                Toast.makeText(ListViewActivity.this, name, Toast.LENGTH_SHORT).show();


            }
        });
    }
}