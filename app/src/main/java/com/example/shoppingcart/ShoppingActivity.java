package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingActivity<EditView> extends AppCompatActivity {

    Spinner stock;
    ArrayList<Items> item = new ArrayList<Items>();
    ArrayList<Items> buy = new ArrayList<Items>();

    Button add_cart, checkout;
    TextView oddItem, evenItem, oddPrice, evenPrice, totalPrice;
    EditView quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        add_cart = (Button) findViewById(R.id.addbtn);
        checkout = (Button) findViewById(R.id.checkbtn);
        quantity = (EditView) findViewById(R.id.qty);

        oddItem = (TextView) findViewById(R.id.odd_item_name);
        oddPrice = (TextView) findViewById(R.id.odd_item_price);
        evenItem = (TextView) findViewById(R.id.even_item_name);
        evenPrice = (TextView) findViewById(R.id.even_item_price);
        totalPrice = (TextView) findViewById(R.id.total_price);

        selectItem();
    }

    public void selectItem(){
        //setup dropdown items
        stock = (Spinner) findViewById(R.id.spinner1);
        String available[] = new String[3];

        item.add(new Items("Apple",20));
        item.add(new Items("Orange",10));
        item.add(new Items("Banana",30));

        for(int i=0; i < item.size(); i++)
            available[i] = item.get(i).getName();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ShoppingActivity.this,
                android.R.layout.simple_list_item_1, available);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stock.setAdapter(myAdapter);

        // selecting the item

        stock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int pos = (int) stock.getSelectedItemId();
                Toast.makeText(ShoppingActivity.this, ""+item.get(pos).getPrice(),Toast.LENGTH_SHORT).show();

                add_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String itemName;
                        double itemPrice = 0, total = 0;
                        buy.add(new Items(item.get(pos).getName(), item.get(pos).getPrice()));

                        String pref_quan = quantity.toString();

                        int count = Integer.getInteger(pref_quan);

                        for(int i=0; i<buy.size(); i++){
                            itemName = buy.get(i).getName();
                            itemPrice = buy.get(i).getPrice();

                            if(i % 2 == 0){
                                evenItem.setText(itemName);
                                evenPrice.setText(""+itemPrice);
                            }

                            else{
                                oddItem.setText(itemName);
                                oddPrice.setText(""+itemPrice);
                            }

                            total += itemPrice;

                            totalPrice.setText(""+total);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ShoppingActivity.this, "Empty",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
