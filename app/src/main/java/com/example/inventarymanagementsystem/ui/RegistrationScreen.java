package com.example.inventarymanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.base.BaseActivity;
import com.example.inventarymanagementsystem.room.entities.Catagory;
import com.example.inventarymanagementsystem.room.entities.User;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;
import com.example.inventarymanagementsystem.utils.Constants;
import com.example.inventarymanagementsystem.utils.EmailValidator;
import com.example.inventarymanagementsystem.utils.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RegistrationScreen extends BaseActivity {
    private TextInputLayout outlined_name;
    private TextInputLayout outlined_phno;
    private TextInputLayout outlined_email;
    private TextInputLayout outlined_address;
    private TextInputLayout outlined_password;
    private TextInputLayout outlined_cnfrmPassword;

    private TextInputEditText et_name;
    private TextInputEditText et_phno;
    private TextInputEditText et_email;
    private TextInputEditText et_address;
    private TextInputEditText et_password;
    private TextInputEditText et_cnfrmPassword;

    private boolean isNameValid;
    private boolean isPhnoValid;
    private boolean isEmailValid;
    private boolean isAddressValid;
    private boolean isPasswordValid;
    private boolean isCnfrmPasswordValid;


    private Button button;
    private Button button2;
    private Toolbar toolbar;

    private InventoryRepo inventoryRepo;

    private SharedPrefManager sharedPreferences;

    List<Catagory> catagoryList = new ArrayList<>();
    int count =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        inventoryRepo = new InventoryRepo(this);
        sharedPreferences = new SharedPrefManager(this);
        setSupportActionBar(toolbar);
        initViews();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextListeners();
        clickListeners();
        observePublishSubjects();
//        initCatagery();

    }

//    private void updateCatagoryDetails(){
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inventoryRepo.updateCatagory(new Catagory(102,"Milky Bar","CANDY102","10","Candies","Pcs",OffsetDateTime.now().plusDays(30),OffsetDateTime.now()));
//            }
//        });
//
//    }

    private void observePublishSubjects() {
        compositeDisposable.add(inventoryRepo.userSignInObserver.flatMap(new Function<User, Observable<User>>() {
            @Override
            public Observable<User> apply(User user) throws Exception {
                return Observable.just(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Consumer<User>() {
              @Override
              public void accept(User user) throws Exception {
                  Toast.makeText(RegistrationScreen.this, "Sign in Success! "+user.getUserName(), Toast.LENGTH_SHORT).show();
                  inventoryRepo.loginUser(user.getPhoneNo(),user.getUserPassword());
              }
          }));
        compositeDisposable.add(inventoryRepo.usererrorObserver.flatMap(new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(String s) throws Exception {
                return Observable.just(s);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Toast.makeText(RegistrationScreen.this, s, Toast.LENGTH_SHORT).show();
            }
        }));
        compositeDisposable.add(
                inventoryRepo.userLoggedObserver.flatMap(new Function<User, Observable<User>>() {
                    @Override
                    public Observable<User> apply(User user) throws Exception {
                        return Observable.just(user);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        sharedPreferences.putString(Constants.USER_NAME,user.getUserName());
                        sharedPreferences.putString(Constants.USER_PHNO,user.getPhoneNo());
                        sharedPreferences.putString(Constants.USER_ADDRESS,user.getUserAddress());
                        sharedPreferences.putString(Constants.USER_EMAIL_ID,user.getUserEmailId());
                        sharedPreferences.putBoolean(Constants.IS_USER_LOGGED_IN,true);
                        Toast.makeText(RegistrationScreen.this, "Login in Success! "+user.getUserName(), Toast.LENGTH_SHORT).show();

                    }
                })
        );
        compositeDisposable.add(
            inventoryRepo.catageryObserver.flatMap(new Function<String, Observable<String>>() {
                @Override
                public Observable<String> apply(String s) throws Exception {
                    return Observable.just(s);
                }
            }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    Toast.makeText(RegistrationScreen.this, "Catagory added  "+s, Toast.LENGTH_SHORT).show();
                }
            })
        );
        compositeDisposable.add(
            inventoryRepo.catageryUpdateObserver.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("==>",""+integer);
            }
        })
        );
    }

    private void clickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNameValid && isEmailValid && isPhnoValid && isAddressValid && isPasswordValid && isCnfrmPasswordValid)
                inventoryRepo.insertSigninUser(new User(et_phno.getText().toString(),et_name.getText().toString(),et_email.getText().toString(),et_address.getText().toString(),et_password.getText().toString()));
                else
                    Toast.makeText(RegistrationScreen.this, "Please fill required fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        outlined_name = findViewById(R.id.outlined_name);
        outlined_phno = findViewById(R.id.outlined_phno);
        outlined_email = findViewById(R.id.outlined_email);
        outlined_address = findViewById(R.id.outlined_address);
        outlined_password = findViewById(R.id.outlined_password);
        outlined_cnfrmPassword = findViewById(R.id.outlined_cnfrmPassword);
//        button2 = findViewById(R.id.button2);

        et_name = findViewById(R.id.et_name);
        et_phno = findViewById(R.id.et_phno);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_password = findViewById(R.id.et_password);
        et_cnfrmPassword = findViewById(R.id.et_cnfrm_password);
        toolbar = findViewById(R.id.toolbar);

        button = findViewById(R.id.button);
    }

    public void editTextListeners(){
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0){
                    outlined_name.setErrorEnabled(true);
                    outlined_name.setError("Name Should not be Empty");
                    isNameValid = false;
                }else{
                    outlined_name.setErrorEnabled(false);
                    isNameValid = true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0){
                    outlined_address.setError("Should not be empty");
                    outlined_address.setErrorEnabled(true);
                    isAddressValid = false;
                }
                else{
                    outlined_phno.setErrorEnabled(false);
                    isAddressValid = true;
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
                    outlined_password.setError("minimum length 6");
                    isPasswordValid = false;
                    outlined_password.setErrorEnabled(true);
                }
                else{
                    outlined_password.setErrorEnabled(false);
                    isPasswordValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_cnfrmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()<6 && !charSequence.toString().equals(et_password.getText().toString())){
                    outlined_cnfrmPassword.setError("Password dosn't match");
                    outlined_cnfrmPassword.setErrorEnabled(true);
                    isCnfrmPasswordValid = false;
                }else{
                    isCnfrmPasswordValid = true;
                    outlined_cnfrmPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!EmailValidator.validateEmail(charSequence.toString()))
                {
                    outlined_email.setErrorEnabled(true);
                    outlined_email.setError("Invalid Email");
                    isEmailValid = false;
                }
                else {
                    isEmailValid = true;
                    outlined_email.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
