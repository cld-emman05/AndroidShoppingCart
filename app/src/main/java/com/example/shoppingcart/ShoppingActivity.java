package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    Spinner stock;
    ArrayList<Items> item = new ArrayList<Items>();
    ArrayList<Items> buy = new ArrayList<Items>();

    Button add_cart, checkout;
    TextView oddItem, evenItem, oddPrice, evenPrice, oddQuan, evenQuan, totalPrice;
    EditText quantity;
    LinearLayout evenQueue, oddQueue, itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        add_cart = (Button) findViewById(R.id.addbtn);
        checkout = (Button) findViewById(R.id.checkbtn);
        quantity = (EditText) findViewById(R.id.qty);

        oddItem = (TextView) findViewById(R.id.odd_item_name);
        oddPrice = (TextView) findViewById(R.id.odd_item_price);
        oddQuan = (TextView) findViewById(R.id.odd_item_qty);
        oddQueue = (LinearLayout) findViewById(R.id.odd_queue);

        evenItem = (TextView) findViewById(R.id.even_item_name);
        evenPrice = (TextView) findViewById(R.id.even_item_price);
        evenQuan = (TextView) findViewById(R.id.even_item_qty);
        evenQueue = (LinearLayout) findViewById(R.id.even_queue);

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
                        LinearLayout odd, even;

                        String itemName;
                        double itemPrice = 0, total = 0;
                        buy.add(new Items(item.get(pos).getName(), item.get(pos).getPrice()));

                        for(int i=0; i < buy.size(); i++){
                            String pref_quan = quantity.getText().toString();

                            if(pref_quan.isEmpty())
                                pref_quan = "0";

                            int count = Integer.parseInt(pref_quan);

                            itemName = buy.get(i).getName();
                            itemPrice = buy.get(i).getPrice()*count;

                            if(i % 2 == 0){
                                evenQuan.setText(pref_quan);
                                evenItem.setText(itemName);
                                evenPrice.setText(""+itemPrice);

                                evenQueue = new LinearLayout(ShoppingActivity.this);

                                //evenQueue.addView(evenQuan);
                                //evenQueue.addView(evenItem);
                                //evenQueue.addView(evenPrice);
                            }

                            else{
                                oddQuan.setText(pref_quan);
                                oddItem.setText(itemName);
                                oddPrice.setText(""+itemPrice);

                                //oddQueue.addView(oddQuan);
                                //oddQueue.addView(oddItem);
                                //oddQueue.addView(oddPrice);
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
