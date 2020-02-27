package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction extends AppCompatActivity implements Serializable {
    Intent intent;
    Button add_more, finalize;
    RecyclerView itemList;

    OrderAdapter adapter;

    ArrayList<Order> buy = new ArrayList<Order>(), checkOut = new ArrayList<Order>();
    String total;

    TextView totalView;
    EditText payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        total = getIntent().getStringExtra("TOTAL");

        checkOut = (ArrayList<Order>) getIntent().getSerializableExtra("ORDERS");

        totalView = (TextView) findViewById(R.id.total_price);
        payment = (EditText) findViewById(R.id.payment);

        add_more = (Button) findViewById(R.id.add_more);
        finalize = (Button) findViewById(R.id.finalize);

        totalView.setText(total);

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction.this, ShoppingActivity.class);
                startActivity(intent);
            }
        });

        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pay_raw, total_raw;
                double total_price, pay_price, change = 0;

                total_raw = totalView.getText().toString();
                pay_raw = payment.getText().toString();

                total_price = Double.parseDouble(total_raw);
                pay_price = Double.parseDouble(pay_raw);

                change = pay_price - total_price;

                if(change < 0){
                    Toast.makeText(Transaction.this, "Insufficient payment",Toast.LENGTH_LONG).show();
                }

                else{
                    intent = new Intent(Transaction.this, FinalizeActivity.class);

                    startActivity(intent);
                }
            }
        });

        itemList = (RecyclerView) findViewById(R.id.order_sum);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(checkOut, this);
        itemList.setAdapter(adapter);
    }
}
