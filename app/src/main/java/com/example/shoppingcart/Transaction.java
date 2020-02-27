package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Transaction extends AppCompatActivity {
    Intent intent;
    Button add_more, finalize;
    RecyclerView itemList;

    OrderAdapter adapter;

    ArrayList<Order> buy = intent.getParcelableArrayListExtra("ORDER_LATEST");
    String total = intent.getStringExtra("TOTAL_AMOUNT");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        add_more = (Button) findViewById(R.id.add_more);
        finalize = (Button) findViewById(R.id.finalize);

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction.this, ShoppingActivity.class);
                startActivity(intent);
            }
        });

        itemList = (RecyclerView) findViewById(R.id.item_list);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(buy, this);
        itemList.setAdapter(adapter);
    }
}
