package com.example.inventarymanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.adapter.PastHistoryAdapter;
import com.example.inventarymanagementsystem.ui.models.PastHistory;
import com.example.inventarymanagementsystem.ui.models.PastHistoryProductItem;

import java.util.ArrayList;
import java.util.List;

public class PastHistoryScreen extends AppCompatActivity {
    private RecyclerView rv_past_history;
    private PastHistoryAdapter pastHistoryAdapter;
    private List<PastHistory> pastHistoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_history_screen);
        rv_past_history = findViewById(R.id.rv_past_history);
        initListDatas();
        pastHistoryAdapter = new PastHistoryAdapter(pastHistoryList);
        rv_past_history.setAdapter(pastHistoryAdapter);

    }
    public void initListDatas(){
        List<PastHistoryProductItem> pastHistoryProductItems = new ArrayList<>();
        pastHistoryProductItems.add(new PastHistoryProductItem("AbTesting","3","15","101","pcs",10));
        pastHistoryProductItems.add(new PastHistoryProductItem("AbTesting","3","15","101","pcs",10));
        pastHistoryProductItems.add(new PastHistoryProductItem("AbTesting","3","15","101","pcs",10));
        pastHistoryProductItems.add(new PastHistoryProductItem("AbTesting","3","15","101","pcs",10));
        pastHistoryProductItems.add(new PastHistoryProductItem("AbTesting","3","15","101","pcs",10));
        pastHistoryList.add(new PastHistory("12/02/2020","Harini",pastHistoryProductItems));
        pastHistoryList.add(new PastHistory("12/02/2020","Harini",pastHistoryProductItems));
        pastHistoryList.add(new PastHistory("12/02/2020","Harini",pastHistoryProductItems));
        pastHistoryList.add(new PastHistory("12/02/2020","Harini",pastHistoryProductItems));
    }
}
