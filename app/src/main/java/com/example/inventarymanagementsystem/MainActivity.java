package com.example.inventarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventarymanagementsystem.base.BaseActivity;
import com.example.inventarymanagementsystem.room.entities.UserHistory;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;
import com.example.inventarymanagementsystem.ui.AddProductsScreen;
import com.example.inventarymanagementsystem.ui.LoginActivity;
import com.example.inventarymanagementsystem.ui.PastHistoryScreen;
import com.example.inventarymanagementsystem.ui.RegistrationScreen;
import com.example.inventarymanagementsystem.ui.ScannerActivity;
import com.example.inventarymanagementsystem.ui.models.PastHistory;
import com.example.inventarymanagementsystem.ui.models.PastHistoryProductItem;
import com.example.inventarymanagementsystem.utils.SharedPrefManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.inventarymanagementsystem.utils.Constants.IS_USER_LOGGED_IN;
import static com.example.inventarymanagementsystem.utils.Constants.USER_NAME;

public class MainActivity extends BaseActivity {
private Toolbar toolbar;
private ConstraintLayout cl_login_layout;
private ConstraintLayout cl_register_layout;
private ConstraintLayout cl_product_layout;
private ConstraintLayout cl_past_history;
private TextView tv_welcome_text;
   private InventoryRepo inventoryRepo;
    private SharedPrefManager sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cl_login_layout = findViewById(R.id.cl_login_layout);
        toolbar = findViewById(R.id.toolbar);
        cl_register_layout = findViewById(R.id.cl_register_layout);
        cl_product_layout = findViewById(R.id.cl_product_layout);
        cl_past_history = findViewById(R.id.cl_past_history);
        setSupportActionBar(toolbar);
        inventoryRepo = new InventoryRepo(this);
        sharedPreferences = new SharedPrefManager(this);
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
//                inventoryRepo.deleteUserHistory();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        cl_product_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getBoolean(IS_USER_LOGGED_IN))
                startActivity(new Intent(MainActivity.this, AddProductsScreen.class));
                else
                    Toast.makeText(MainActivity.this, "Login to Continue!", Toast.LENGTH_SHORT).show();
            }
        });
        cl_past_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getBoolean(IS_USER_LOGGED_IN))
                startActivity(new Intent(MainActivity.this, PastHistoryScreen.class));
                else
                    Toast.makeText(MainActivity.this, "Login to Continue!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.qr_code_scanner:
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                sharedPreferences.clearPreferences();
                Toast.makeText(MainActivity.this, "Logged out Successfully!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
