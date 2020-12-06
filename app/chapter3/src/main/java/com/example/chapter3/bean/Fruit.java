package com.example.chapter3.bean;

public class Fruit {
    private String fruitName;
    private int fruitImage;

   public Fruit(String fruitName,int fruitImage){
       this.fruitImage=fruitImage;
       this.fruitName=fruitName;
   }

    public String getFruitName() {
        return fruitName;
    }

    public int getFruitImage() {
        return fruitImage;
    }
}
