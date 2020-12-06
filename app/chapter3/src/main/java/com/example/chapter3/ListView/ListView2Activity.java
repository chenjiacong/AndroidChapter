package com.example.chapter3.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import com.example.chapter3.R;
import com.example.chapter3.adapter.FruitAdapter;
import com.example.chapter3.bean.Fruit;

public class ListView2Activity extends AppCompatActivity {
private List<Fruit> fruitList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        initFruit();
        ListView listView=findViewById(R.id.listView2);

        FruitAdapter fruitAdapter=new FruitAdapter(this,R.layout.fruit_item,fruitList);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit=fruitList.get(position);
                Toast.makeText(ListView2Activity.this,fruit.getFruitName(),Toast.LENGTH_SHORT).show();
            }
        });
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