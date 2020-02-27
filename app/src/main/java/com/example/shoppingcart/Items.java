package com.example.shoppingcart;

import java.io.Serializable;

public class Items implements Serializable {
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
