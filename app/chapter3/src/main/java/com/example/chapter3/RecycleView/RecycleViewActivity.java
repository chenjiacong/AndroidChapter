package com.example.chapter3.RecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import com.example.chapter3.R;
import com.example.chapter3.adapter.RecycleViewAdapter;
import com.example.chapter3.bean.Fruit;

public class RecycleViewActivity extends AppCompatActivity {
private List<Fruit> fruitList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        RecyclerView recyclerView=findViewById(R.id.recycleView);
        initFruit();
        LinearLayoutManager manager=new LinearLayoutManager(this);
      // manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecycleViewAdapter adapter=new RecycleViewAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }
    private void initFruit() {
        for (int i=0;i<3;i++) {
            Fruit apple = new Fruit("apple", R.drawable.apple);
            fruitList.add(apple);
            Fruit pear = new Fruit("pear", R.drawable.peach);
            fruitList.add(pear);
            Fruit orange = new Fruit("orange", R.drawable.orange);
            fruitList.add(orange);
            Fruit banana = new Fruit("banana", R.drawable.banana);
            fruitList.add(banana);
            Fruit peach = new Fruit("peach", R.drawable.peach);
            fruitList.add(peach
            );
        }
    }
}