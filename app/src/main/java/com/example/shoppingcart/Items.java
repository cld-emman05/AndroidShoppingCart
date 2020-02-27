package com.example.shoppingcart;

public class Items{
    protected String name;
    protected double price;

    public Items(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }
}
