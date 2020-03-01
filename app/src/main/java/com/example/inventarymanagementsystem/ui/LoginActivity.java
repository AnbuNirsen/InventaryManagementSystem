package com.example.inventarymanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.base.BaseActivity;
import com.example.inventarymanagementsystem.room.entities.User;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;
import com.example.inventarymanagementsystem.utils.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends BaseActivity {
    private TextInputLayout outlined_phno;
    private TextInputLayout outlined_password;

    private TextInputEditText et_phno;
    private TextInputEditText et_password;

    private Button button;

    //validation booleans
    private boolean isPhnoValid = false;
    private boolean isPasswordValid = false;


    private InventoryRepo inventoryRepo;

    private SharedPrefManager sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inventoryRepo = new InventoryRepo(this);
        sharedPreferences = new SharedPrefManager(this);
        initViews();
        editClickListeners();
        clickListeners();
    }

    private void clickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPasswordValid && isPhnoValid)
                    inventoryRepo.loginUser(et_phno.getText().toString(),et_password.getText().toString());
                else
                    Toast.makeText(LoginActivity.this, "All Fields are Required!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editClickListeners() {
        et_phno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()<10){
                    outlined_phno.setErrorEnabled(true);
                    outlined_phno.setError("Enter valid Phno");
                    isPhnoValid = false;
                }
                else{
                    outlined_phno.setErrorEnabled(false);
                    isPhnoValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()<6){
                    outlined_password.setError("minimum length 6!");
                    outlined_password.setErrorEnabled(true);
                    isPasswordValid = false;
                }else{
                    outlined_password.setErrorEnabled(false);
                    isPasswordValid = true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initViews() {
        outlined_phno = findViewById(R.id.outlined_phno);
        outlined_password = findViewById(R.id.outlined_password);
        et_phno = findViewById(R.id.et_phno);
        et_password = findViewById(R.id.et_password);
        button = findViewById(R.id.button);
    }
}
