package com.example.inventarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventarymanagementsystem.ui.AddProductsScreen;
import com.example.inventarymanagementsystem.ui.LoginActivity;
import com.example.inventarymanagementsystem.ui.PastHistoryScreen;
import com.example.inventarymanagementsystem.ui.RegistrationScreen;
import com.example.inventarymanagementsystem.ui.models.PastHistory;

public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
private ConstraintLayout cl_login_layout;
private ConstraintLayout cl_register_layout;
private ConstraintLayout cl_product_layout;
private ConstraintLayout cl_past_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cl_login_layout = findViewById(R.id.cl_login_layout);
        cl_register_layout = findViewById(R.id.cl_register_layout);
        cl_product_layout = findViewById(R.id.cl_product_layout);
        cl_past_history = findViewById(R.id.cl_past_history);
        clickListeners();

    }

    public void clickListeners(){
        cl_register_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationScreen.class));
            }
        });
        cl_login_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        cl_product_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddProductsScreen.class));
            }
        });
        cl_past_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PastHistoryScreen.class));
            }
        });
    }
}
