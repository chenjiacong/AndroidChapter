package com.example.chapter3.RecycleView;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.chapter3.R;
import com.example.chapter3.adapter.RecyclerView2Adapter;
import com.example.chapter3.bean.Fruit;

public class RecyclerView2Activity extends AppCompatActivity {
    public List<Fruit> fruitList = new ArrayList<>();
    private RecyclerView recycleview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);
        initView();
        initFruit();

    }
    private void initView() {
        recycleview2 = (RecyclerView) findViewById(R.id.recycleview2);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recycleview2.setLayoutManager(layoutManager);
        RecyclerView2Adapter fruit2Adapter=new RecyclerView2Adapter(fruitList);
        recycleview2.setAdapter(fruit2Adapter);

    }
    private void initFruit() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("apple"), R.drawable.apple);
            fruitList.add(apple);
            Fruit pear = new Fruit(getRandomLengthName("pear"), R.drawable.peach);
            fruitList.add(pear);
            Fruit orange = new Fruit(getRandomLengthName("orange"), R.drawable.orange);
            fruitList.add(orange);
            Fruit banana = new Fruit(getRandomLengthName("banana"), R.drawable.banana);
            fruitList.add(banana);
            Fruit peach = new Fruit(getRandomLengthName("peach"), R.drawable.peach);
            fruitList.add(peach
            );
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }


}