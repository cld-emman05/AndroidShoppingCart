package com.example.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;

// for the list of items being ordered by the user
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    TextView evenItem, evenPrice,evenQuan;
    private LayoutInflater inflater;
    private ArrayList<Order> itemOrders;
    private Context context;

    public OrderAdapter(ArrayList<Order> itemOrders, Context context){
        this.itemOrders = itemOrders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order itemOrder = itemOrders.get(position);

        holder.evenQuan.setText(""+itemOrder.getQuantity());
        holder.evenItem.setText(itemOrder.item.getName());
        holder.evenPrice.setText(""+itemOrder.item.getPrice());
    }

    @Override
    public int getItemCount() {
        return itemOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView evenQuan, evenItem, evenPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            evenQuan = (TextView) itemView.findViewById(R.id.item_qty);
            evenItem = (TextView) itemView.findViewById(R.id.item_name);
            evenPrice = (TextView) itemView.findViewById(R.id.item_price);
        }
    }

}

