package com.example.shoppingcart;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    protected Items item;
    protected int quantity;
    private double total;

    public Order(String item_name, double item_price, int quantity){
        this.item = new Items(item_name, item_price);
        this.quantity = quantity;
    }

    public double getTotal(){
        return total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }
}

