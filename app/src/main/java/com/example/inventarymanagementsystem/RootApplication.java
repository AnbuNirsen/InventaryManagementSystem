package com.example.inventarymanagementsystem;

import android.app.Application;

import com.example.inventarymanagementsystem.room.entities.Catagory;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RootApplication extends Application {
    List<Catagory> catagoryList = new ArrayList<>();
    InventoryRepo inventoryRepo;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public void onCreate() {
        super.onCreate();
        inventoryRepo = new InventoryRepo(this);
        inventoryRepo.deleteCategory();
        compositeDisposable.add(
            inventoryRepo.catagoryDeleteObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    initCatagery();
                }
            })
        );



    }


    public void initCatagery(){
        catagoryList.add(new Catagory(101,"Five Star","CANDY101","20","Candies","Pcs", OffsetDateTime.now().plusDays(18),OffsetDateTime.now()));
        catagoryList.add(new Catagory(102,"Milky Bar","CANDY102","10","Candies","Pcs",OffsetDateTime.now().plusDays(120),OffsetDateTime.now()));
        catagoryList.add(new Catagory(103,"Kit Kat","CANDY103","30","Candies","Pcs",OffsetDateTime.now().plusDays(32),OffsetDateTime.now()));
        catagoryList.add(new Catagory(104,"Colgate","PHASTE201","40","Phaste","Pcs",OffsetDateTime.now().plusDays(30),OffsetDateTime.now()));
        catagoryList.add(new Catagory(105,"CloseUp","PHASTE202","50","Phaste","Pcs",OffsetDateTime.now().plusDays(50),OffsetDateTime.now()));
        catagoryList.add(new Catagory(106,"M&M's","BISCUI301","20","Biscuit","Pcs",OffsetDateTime.now().plusDays(210),OffsetDateTime.now()));
        catagoryList.add(new Catagory(107,"Oreo","BISCUI302","10","Biscuit","Pcs",OffsetDateTime.now().plusDays(21),OffsetDateTime.now()));
        catagoryList.add(new Catagory(108,"Snickers","CANDY104","10","Candies","Pcs",OffsetDateTime.now().plusDays(32),OffsetDateTime.now()));
        catagoryList.add(new Catagory(109,"Doritos","CHIPS201","10","Chips","Pcs",OffsetDateTime.now().plusDays(10),OffsetDateTime.now()));
        catagoryList.add(new Catagory(110,"Lays","CHIPS202","50","Chips","Pcs",OffsetDateTime.now().plusDays(56),OffsetDateTime.now()));
        catagoryList.add(new Catagory(111,"Kurkure","CHIPS203","20","Chips","Pcs",OffsetDateTime.now().plusDays(110),OffsetDateTime.now()));
        catagoryList.add(new Catagory(112,"Coca-Cola","DRINKS401","60","Drinks","ltrs",OffsetDateTime.now().plusDays(150),OffsetDateTime.now()));
        catagoryList.add(new Catagory(113,"Fanta","DRINKS402","40","Drinks","ltrs",OffsetDateTime.now().plusDays(70),OffsetDateTime.now()));
        catagoryList.add(new Catagory(114,"Seven Up","DRINKS403","20","Drinks","ltrs",OffsetDateTime.now().plusDays(90),OffsetDateTime.now()));
        catagoryList.add(new Catagory(115,"Sprite","DRINKS404","70","Drinks","ltrs",OffsetDateTime.now().plusDays(110),OffsetDateTime.now()));
        for(Catagory catagory:catagoryList){
            inventoryRepo.addCatagery(catagory);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
       if(!compositeDisposable.isDisposed())
           compositeDisposable.dispose();
    }
}
