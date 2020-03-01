package com.example.inventarymanagementsystem.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.models.ProductIItem;

import java.util.ArrayList;

public class AddProductsAdapter extends RecyclerView.Adapter<AddProductsAdapter.ViewHolder> {

    private ArrayList<ProductIItem> productList = new ArrayList<>();

    public AddProductsAdapter(ArrayList<ProductIItem> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_price.setText(productList.get(position).getPrice());
        holder.tv_product_name.setText(productList.get(position).getProductName());
        holder.tv_qnty.setText(productList.get(position).getQuantity());
        holder.tv_total.setText(productList.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_product_name;
        public TextView tv_qnty;
        public TextView tv_price;
        public TextView tv_total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_qnty = itemView.findViewById(R.id.tv_qnty);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_total = itemView.findViewById(R.id.tv_total);

        }
    }
}
