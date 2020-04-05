package com.example.inventarymanagementsystem.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.models.PastHistory;
import com.example.inventarymanagementsystem.ui.models.PastHistoryProductItem;

import java.util.List;

public class PastHistoryProductItemAdapter extends RecyclerView.Adapter<PastHistoryProductItemAdapter.ViewHolder> {
    List<PastHistoryProductItem> pastHistoryProductItemList;

    public PastHistoryProductItemAdapter(List<PastHistoryProductItem> pastHistoryProductItemList) {
        this.pastHistoryProductItemList = pastHistoryProductItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_past_history_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_past_history_product_name.setText(pastHistoryProductItemList.get(position).getProductName());
        holder.tv_product_id.setText("Product ID: "+pastHistoryProductItemList.get(position).getProductId());
        holder.tv_product_price.setText("₹ "+String.valueOf(Integer.parseInt(pastHistoryProductItemList.get(position).getProductQnty()) * Integer.parseInt(pastHistoryProductItemList.get(position).getProductPrice())));
        holder.tv_product_qnty_single_price.setText("("+pastHistoryProductItemList.get(position).getQntyType()+" * "+" ₹ "+pastHistoryProductItemList.get(position).getProductPrice()+")");

    }

    @Override
    public int getItemCount() {
        return pastHistoryProductItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_past_history_product_name;
        TextView tv_product_id;
        TextView tv_product_price;
        TextView tv_product_qnty_single_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_past_history_product_name = itemView.findViewById(R.id.tv_past_history_product_name);
            tv_product_id = itemView.findViewById(R.id.tv_product_id);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            tv_product_qnty_single_price = itemView.findViewById(R.id.tv_product_qnty_single_price);

        }
    }
}
