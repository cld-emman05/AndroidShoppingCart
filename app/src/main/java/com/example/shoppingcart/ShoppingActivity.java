package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    Spinner stock;
    ArrayList<Items> item = new ArrayList<Items>();
    ArrayList<Items> buy = new ArrayList<Items>();

    Button add_cart, checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        item.add(new Items("Apple",20));
        item.add(new Items("Orange",10));
        item.add(new Items("Banana",30));

        String available[] = new String[3];

        for(int i=0; i < item.size(); i++)
            available[i] = item.get(i).getName();

        stock = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ShoppingActivity.this,
                    android.R.layout.simple_list_item_1, available);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stock.setAdapter(myAdapter);
    }
}
