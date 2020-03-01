package com.example.inventarymanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.adapter.AddProductsAdapter;
import com.example.inventarymanagementsystem.ui.adapter.SimpleListAdapter;
import com.example.inventarymanagementsystem.ui.models.ProductIItem;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class AddProductsScreen extends AppCompatActivity {

    SimpleListAdapter simplelistAdapter;
    private SearchableSpinner searchableSpinner1;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private final ArrayList<ProductIItem> productIItems = new ArrayList<>();
    private AddProductsAdapter addProductsAdapter;
    private RecyclerView rv_addproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_screen);
        initListValues();
        initViews();
        simplelistAdapter = new SimpleListAdapter(this, mStrings);
        addProductsAdapter = new AddProductsAdapter(productIItems);
        rv_addproduct.setAdapter(addProductsAdapter);

        searchableSpinner1.setAdapter(simplelistAdapter);
        searchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener);
        searchableSpinner1.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                searchableSpinner1.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });



    }
    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(AddProductsScreen.this, "Item on position " + position + " : " + simplelistAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(AddProductsScreen.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


    private void initViews() {
        searchableSpinner1 = findViewById(R.id.searchableSpinner);
        rv_addproduct = findViewById(R.id.rv_addproduct);
        rv_addproduct.setHasFixedSize(true);
    }

    private void initListValues() {
        mStrings.add("Brigida Kurz");
        mStrings.add("Tracy Mckim");
        mStrings.add("Iesha Davids");
        mStrings.add("Ozella Provenza");
        mStrings.add("Florentina Carriere");
        mStrings.add("Geri Eiler");
        mStrings.add("Tammara Belgrave");
        mStrings.add("Ashton Ridinger");
        mStrings.add("Jodee Dawkins");
        mStrings.add("Florine Cruzan");
        mStrings.add("Latia Stead");
        mStrings.add("Kai Urbain");
        mStrings.add("Liza Chi");
        mStrings.add("Clayton Laprade");
        mStrings.add("Wilfredo Mooney");
        mStrings.add("Roseline Cain");
        mStrings.add("Chadwick Gauna");
        mStrings.add("Carmela Bourn");
        mStrings.add("Valeri Dedios");
        mStrings.add("Calista Mcneese");
        mStrings.add("Willard Cuccia");
        mStrings.add("Ngan Blakey");
        mStrings.add("Reina Medlen");
        mStrings.add("Fabian Steenbergen");
        mStrings.add("Edmond Pine");
        mStrings.add("Teri Quesada");
        mStrings.add("Vernetta Fulgham");
        mStrings.add("Winnifred Kiefer");
        mStrings.add("Chiquita Lichty");
        mStrings.add("Elna Stiltner");
        mStrings.add("Carly Landon");
        mStrings.add("Hans Morford");
        mStrings.add("Shawanna Kapoor");
        mStrings.add("Thomasina Naron");
        mStrings.add("Melba Massi");
        mStrings.add("Sal Mangano");
        mStrings.add("Mika Weitzel");
        mStrings.add("Phylis France");
        mStrings.add("Illa Winzer");
        mStrings.add("Kristofer Boyden");
        mStrings.add("Idalia Cryan");
        mStrings.add("Jenni Sousa");
        mStrings.add("Eda Forkey");
        mStrings.add("Birgit Rispoli");
        mStrings.add("Janiece Mcguffey");
        mStrings.add("Barton Busick");
        mStrings.add("Gerald Westerman");
        mStrings.add("Shalanda Baran");
        mStrings.add("Margherita Pazos");
        mStrings.add("Yuk Fitts");

        productIItems.add(new ProductIItem("Kitkat chocklate","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
        productIItems.add(new ProductIItem("Kitkat ticket chicken b","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
        productIItems.add(new ProductIItem("Kitkat","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
        productIItems.add(new ProductIItem("Kitkat","2","120","240","Candy","101","12/02/2020","13/02/20","122"));
    }
}
