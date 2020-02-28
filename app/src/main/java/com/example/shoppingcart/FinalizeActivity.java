package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FinalizeActivity extends AppCompatActivity {
    Button main;

    ArrayList<Order> checkOut = new ArrayList<Order>(), soldItems = new ArrayList<Order>();

    TextView total_price, change_price, pay_price;

    Intent intent;
    String total, pay;
    double change;

    RecyclerView itemList;
    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

        soldItems = (ArrayList<Order>) getIntent().getSerializableExtra("CHECKOUTS");

        main = (Button) findViewById(R.id.main_menu);
        total_price = (TextView) findViewById(R.id.total_price);
        pay_price = (TextView) findViewById(R.id.pay_price);
        change_price = (TextView) findViewById(R.id.change_price);

        itemList = (RecyclerView) findViewById(R.id.item_list);

        total = getIntent().getStringExtra("TOTAL");
        pay = getIntent().getStringExtra("PAY");
        change = getIntent().getDoubleExtra("CHANGE", 0);

        total_price.setText(total);
        pay_price.setText(pay);
        change_price.setText("" + change);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(FinalizeActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        itemList = (RecyclerView) findViewById(R.id.order_items);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(soldItems, this);
        itemList.setAdapter(adapter);

    }
}
