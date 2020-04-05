package com.example.inventarymanagementsystem.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.base.BaseActivity;
import com.example.inventarymanagementsystem.room.callbacks.UserCallBack;
import com.example.inventarymanagementsystem.room.entities.Catagory;
import com.example.inventarymanagementsystem.room.entities.UserHistory;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;
import com.example.inventarymanagementsystem.ui.adapter.AddProductsAdapter;
import com.example.inventarymanagementsystem.ui.adapter.ProductListAdapter;
import com.example.inventarymanagementsystem.ui.adapter.SimpleListAdapter;
import com.example.inventarymanagementsystem.ui.models.ProductIItem;
import com.example.inventarymanagementsystem.utils.Constants;
import com.example.inventarymanagementsystem.utils.SharedPrefManager;

import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.inventarymanagementsystem.utils.UtillFunctions.convertNumberToRupee;

public class AddProductsScreen extends BaseActivity implements UserCallBack {

    SimpleListAdapter simplelistAdapter;
    SimpleListAdapter simpleProductlistAdapter;
    private SearchableSpinner searchableSpinner1;
    private int updateSerialNo =0 ;
    private SearchableSpinner searchableSpinnerProduct;
    private final ArrayList<String> mCatagerylist = new ArrayList<>();
    private final ArrayList<String> mProductslist = new ArrayList<>();
    private final ArrayList<Catagory> mCatageryActuallist = new ArrayList<>();
    private final ArrayList<ProductIItem> productIItems = new ArrayList<>();
    private AddProductsAdapter addProductsAdapter;
    private RecyclerView rv_addproduct;
    private InventoryRepo inventoryRepo;
    private TextView tv_value_productId;
    private TextView tv__productQnty;
    private TextView tv_value_productPrice;
    private TextView tv_value_TotalPrice;
    private EditText tv_value_productQnty;
    private TextView tv_value_manufacture_date;
    private TextView tv_value_expire_date;
    private Button btn_add;

    private Catagory selectedProduct;
    private Toolbar toolbar;

    private SharedPrefManager sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_screen);
//        initListValues();
        initViews();
        inventoryRepo = new InventoryRepo(this);
        sharedPreferences = new SharedPrefManager(this);

        inventoryRepo.getCatagery();
        inventoryRepo.getHistory(sharedPreferences.getString(Constants.USER_PHNO));
//
        compositeDisposable.add(
            inventoryRepo.catageryListObserver.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Catagory>>() {
                @Override
                public void accept(List<Catagory> catagoryList) throws Exception {
                    for(Catagory catagory:catagoryList){
                        Log.d("==>",""+catagory.toString());
                        if(!mCatagerylist.contains(catagory.getCatagoryName()))
                        mCatagerylist.add(catagory.getCatagoryName());
                        mCatageryActuallist.add(catagory);
                    }
                    simplelistAdapter = new SimpleListAdapter(getBaseContext(), mCatagerylist);

                    searchableSpinner1.setAdapter(simplelistAdapter);
                    searchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener);
                    searchableSpinner1.setStatusListener(new IStatusListener() {
                        @Override
                        public void spinnerIsOpening() {
                            searchableSpinner1.hideEdit();
                            searchableSpinnerProduct.setFocusable(false);
                        }

                        @Override
                        public void spinnerIsClosing() {
                            searchableSpinnerProduct.setFocusable(true);
                        }
                    });
                }
            })
        );
        compositeDisposable.add(
           inventoryRepo.userHistoryListObserver.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UserHistory>>() {
               @Override
               public void accept(List<UserHistory> userHistories) throws Exception {
                   Log.d("==>123<==",""+userHistories);
                   productIItems.clear();
                   for(UserHistory userHistory:userHistories)
                   productIItems.add(new ProductIItem(userHistory.getProductName(),userHistory.getProductQuantity(),userHistory.getProductPrice(),userHistory.getTotalPrice(),userHistory.getCatagory(),userHistory.getProductId(),userHistory.getManufactureDate(),userHistory.getExpireDate(),String.valueOf(userHistory.getCatagoryId()),userHistory.getSno()));
                   addProductsAdapter = new AddProductsAdapter(productIItems,AddProductsScreen.this::userCallBack);
                   rv_addproduct.setAdapter(addProductsAdapter);

               }
           })
        );
        compositeDisposable.add(
            inventoryRepo.updateUserHistory.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if(aBoolean){
                        Toast.makeText(AddProductsScreen.this,"Update Successfully!!",Toast.LENGTH_SHORT).show();
                    }
                }
            })
        );

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_add.getText().toString().equalsIgnoreCase("Add Product")){
                    if(sharedPreferences.getBoolean(Constants.IS_USER_LOGGED_IN)){
                        inventoryRepo.addHistory(new UserHistory(0,
                            sharedPreferences.getString(Constants.USER_PHNO),
                            OffsetDateTime.now(),
                            sharedPreferences.getString(Constants.USER_NAME),
                            selectedProduct.getProductName(),
                            tv_value_productQnty.getText().toString(),
                            tv_value_productPrice.getText().toString().substring(2),
                            selectedProduct.getCatagoryName(),
                            tv_value_productId.getText().toString(),
                            String.valueOf(selectedProduct.getCatagoryId()),
                            tv_value_TotalPrice.getText().toString().substring(2),
                            selectedProduct.getProductManufactureDate(),
                            selectedProduct.getProdctExpDate()
                        ));
                        getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                        );
                    }else{
                        Toast.makeText(AddProductsScreen.this, "Please Login to Continue!", Toast.LENGTH_SHORT).show();
                    }
                }else if(btn_add.getText().toString().equalsIgnoreCase("Update Product")){
                    if(sharedPreferences.getBoolean(Constants.IS_USER_LOGGED_IN)){
                        inventoryRepo.updateUserHistory(new UserHistory(updateSerialNo,
                            sharedPreferences.getString(Constants.USER_PHNO),
                            OffsetDateTime.now(),
                            sharedPreferences.getString(Constants.USER_NAME),
                            selectedProduct.getProductName(),
                            tv_value_productQnty.getText().toString(),
                            tv_value_productPrice.getText().toString().substring(2),
                            selectedProduct.getCatagoryName(),
                            tv_value_productId.getText().toString(),
                            String.valueOf(selectedProduct.getCatagoryId()),
                            tv_value_TotalPrice.getText().toString().substring(2),
                            selectedProduct.getProductManufactureDate(),
                            selectedProduct.getProdctExpDate()
                        ));
                        getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                        );
                        btn_add.setText("Add Product");
                    }else{
                        Toast.makeText(AddProductsScreen.this, "Please Login to Continue!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(AddProductsScreen.this, "Item on position " + position + " : " + simplelistAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
            mProductslist.clear();
            for(Catagory catagory:mCatageryActuallist){
                    if(catagory.getCatagoryName().equals(simplelistAdapter.getItem(position) ))
                    mProductslist.add(catagory.getProductName());
            }
            simpleProductlistAdapter = new SimpleListAdapter(getBaseContext(), mProductslist);

            searchableSpinnerProduct.setAdapter(simpleProductlistAdapter);
            searchableSpinnerProduct.setOnItemSelectedListener(mOnItemSelectedProductListener);
            searchableSpinnerProduct.setStatusListener(new IStatusListener() {
                @Override
                public void spinnerIsOpening() {
                    searchableSpinnerProduct.hideEdit();
                    searchableSpinner1.setFocusable(false);
                }

                @Override
                public void spinnerIsClosing() {
                    searchableSpinner1.setFocusable(true);
                }
            });
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(AddProductsScreen.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

    private OnItemSelectedListener mOnItemSelectedProductListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Catagory selectedCatagory = null;
            for(Catagory catagory: mCatageryActuallist){
                if(catagory.getProductName().equals(simpleProductlistAdapter.getItem(position))){
                    selectedCatagory = catagory;
                    break;
                }
            }
            if(selectedCatagory != null){
                selectedProduct = selectedCatagory;
                tv_value_productId.setText(selectedCatagory.getProductId());
                tv__productQnty.setText(selectedCatagory.getQntyType());
                tv_value_productPrice.setText(convertNumberToRupee(selectedCatagory.getProductPrice()));
                tv_value_TotalPrice.setText(convertNumberToRupee(selectedCatagory.getProductPrice()));
                Catagory finalSelectedCatagory = selectedCatagory;
                tv_value_productQnty.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(isNumeric(s.toString())){
                            if(s.toString().length()<0){
                                tv_value_TotalPrice.setText(convertNumberToRupee(finalSelectedCatagory.getProductPrice()));
                            }
                            if(Integer.parseInt(s.toString())==0)
                                tv_value_TotalPrice.setText(convertNumberToRupee((finalSelectedCatagory.getProductPrice())));
                            else if(Integer.parseInt(s.toString())>0){
                                int productPrice = Integer.parseInt(s.toString()) * Integer.parseInt(finalSelectedCatagory.getProductPrice());
                                tv_value_TotalPrice.setText(convertNumberToRupee((String.valueOf(productPrice))));
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                tv_value_expire_date.setText(selectedCatagory.getProdctExpDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                tv_value_manufacture_date.setText(selectedCatagory.getProductManufactureDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            }

            Toast.makeText(AddProductsScreen.this, "Item on position " + position + " : " + simpleProductlistAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(AddProductsScreen.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

    private static boolean isNumeric(String str)
    {
        if(str.length()==0)
            return false;
        return str.matches("-?\\d+(.\\d+)?");
    }
    private void initViews() {
        searchableSpinner1 = findViewById(R.id.searchableSpinner);
        searchableSpinnerProduct = findViewById(R.id.searchableSpinnerProduct);
        rv_addproduct = findViewById(R.id.rv_addproduct);
        tv_value_productId = findViewById(R.id.tv_value_productId);
        tv__productQnty = findViewById(R.id.tv__productQnty);
        tv_value_productPrice = findViewById(R.id.tv_value_productPrice);
        tv_value_TotalPrice = findViewById(R.id.tv_value_TotalPrice);
        tv_value_productQnty = findViewById(R.id.tv_value_productQnty);
        tv_value_manufacture_date = findViewById(R.id.tv_value_manufacture_date);
        tv_value_expire_date = findViewById(R.id.tv_value_expire_date);
        toolbar = findViewById(R.id.toolbar);
        btn_add = findViewById(R.id.btn_add);
        rv_addproduct.setHasFixedSize(true);
    }

    @Override
    public void userCallBack(ProductIItem productIItems) {
        updateSerialNo = productIItems.getSno();
        searchableSpinner1.setSelectedItem(productIItems.getCatagory());
        searchableSpinnerProduct.setSelectedItem(productIItems.getProductName());
        tv_value_productId.setText(productIItems.getProductId());
        tv_value_productQnty.setText(productIItems.getQuantity());
        tv_value_productPrice.setText(convertNumberToRupee(productIItems.getPrice()));
        tv_value_TotalPrice.setText(convertNumberToRupee(productIItems.getTotal()));
        tv_value_manufacture_date.setText(convertNumberToRupee(productIItems.getManufactureDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        tv_value_expire_date.setText(convertNumberToRupee(productIItems.getExpireDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        btn_add.setText("Update Product");
    }
//
//    private void initListValues() {
//        productIItems.add(new ProductIItem("Kitkat chocklate","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
//        productIItems.add(new ProductIItem("Kitkat ticket chicken b","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
//        productIItems.add(new ProductIItem("Kitkat","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
//        productIItems.add(new ProductIItem("Kitkat","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
//    }
}
