package com.example.chapter3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

import com.example.chapter3.R;
import com.example.chapter3.bean.Fruit;

public class RecyclerView2Adapter extends RecyclerView.Adapter<RecyclerView2Adapter.View2Holder> {
    private List<Fruit> fruits = new ArrayList<>();

    public RecyclerView2Adapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public RecyclerView2Adapter.View2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item2, parent, false);
        final View2Holder viewHolder = new View2Holder(view);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = fruits.get(position);
                Toast.makeText(v.getContext(), fruit.getFruitName(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return viewHolder;
    }


    public void onBindViewHolder(View2Holder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.imageView.setImageResource(fruit.getFruitImage());
        holder.textView.setText(fruit.getFruitName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class View2Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public View2Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
            }
