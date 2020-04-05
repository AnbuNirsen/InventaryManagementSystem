package com.example.inventarymanagementsystem.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.models.PastHistory;

import java.util.List;

public class PastHistoryAdapter extends RecyclerView.Adapter<PastHistoryAdapter.ViewHolder> {
    private List<PastHistory> pastHistoryList;

    public PastHistoryAdapter(List<PastHistory> pastHistoryList) {
        this.pastHistoryList = pastHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.past_history_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_customer_date.setText(pastHistoryList.get(position).getDate());
        holder.tv_total_amount.setText(pastHistoryList.get(position).getName());
        holder.tv_customer_name.setText(pastHistoryList.get(position).getName());
        int totalValue = 0;
        for(int i=0;i<pastHistoryList.get(position).getPastHistoryList().size();i++){
            Log.d("pos: "+position+" i: "+i+" =>",pastHistoryList.get(position).getPastHistoryList().get(i).toString());
            totalValue = totalValue + pastHistoryList.get(position).getPastHistoryList().get(i).getTotalAmount();
            Log.d("==>total Value",totalValue+" "+ pastHistoryList.get(position).getPastHistoryList().get(i).getTotalAmount());
        }
        holder.tv_total_amount.setText("₹ "+String.valueOf(totalValue));
        PastHistoryProductItemAdapter pastHistoryProductItemAdapter = new PastHistoryProductItemAdapter(pastHistoryList.get(position).getPastHistoryList());
        holder.rv_product_list.setAdapter(pastHistoryProductItemAdapter);
    }

    @Override
    public int getItemCount() {
        return pastHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_total_amount;
        public TextView tv_customer_date;
        public TextView tv_customer_name;
        public RecyclerView rv_product_list;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_total_amount = itemView.findViewById(R.id.tv_total_amount);
            tv_customer_date = itemView.findViewById(R.id.tv_customer_date);
            tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
            rv_product_list = itemView.findViewById(R.id.rv_product_list);
        }
    }
}
