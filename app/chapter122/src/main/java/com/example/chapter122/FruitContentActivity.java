package com.example.chapter122;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitContentActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE = "fruit_image";
    private ImageView fruit_image_view;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toobar;
    private AppBarLayout appBar;
    private TextView fruit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_content);
        initView();
    }

    private void initView() {
        Intent intent=getIntent();
        String content=intent.getStringExtra(FRUIT_NAME);
        int fruitImageId=intent.getIntExtra(FRUIT_IMAGE,0);
        fruit_image_view = (ImageView) findViewById(R.id.fruit_image_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_toobar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toobar);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        fruit_content = (TextView) findViewById(R.id.fruit_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsing_toobar.setTitle(content);
        Glide.with(this).load(fruitImageId).into(fruit_image_view);
        String fruitContent=generateFruitContent(content);
        fruit_content.setText(fruitContent);
    }

    private String generateFruitContent(String content) {
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<500;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}