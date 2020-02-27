package com.example.shoppingcart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    Spinner stock;
    ArrayList<Items> item = new ArrayList<Items>();
    ArrayList<Order> buy = new ArrayList<Order>();

    Button add_cart, checkout;
    TextView evenItem, evenPrice, evenQuan, totalPrice;
    EditText quantity;

    RecyclerView itemList;
    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shopping);
        setContentView(R.layout.activity_shopping);

        add_cart = (Button) findViewById(R.id.addbtn);
        checkout = (Button) findViewById(R.id.checkbtn);
        quantity = (EditText) findViewById(R.id.qty);

        totalPrice = (TextView) findViewById(R.id.total_price);

        selectItem();
    }

    public void addCart(int pos){
        double total = 0, itemPrice = 0;
        String pref_quan = quantity.getText().toString();

        if(pref_quan.isEmpty())
            pref_quan = "0";

        int count = Integer.parseInt(pref_quan);

        buy.add(new Order(item.get(pos).getName(), item.get(pos).getPrice(), count));

        for(int i = 0; i < buy.size(); i++)
            itemPrice += buy.get(i).item.price * buy.get(i).quantity;

            total = itemPrice;
            totalPrice.setText(""+total);

        itemList = (RecyclerView) findViewById(R.id.item_list);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(buy, this);
        itemList.setAdapter(adapter);
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
                final int pos = (int) id;
                Toast.makeText(ShoppingActivity.this, ""+item.get(pos).getPrice(),Toast.LENGTH_SHORT).show();

                add_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addCart(pos);
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
