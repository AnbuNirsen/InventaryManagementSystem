package com.example.inventarymanagementsystem.room.repository;

import android.content.Context;
import android.util.Log;

import com.example.inventarymanagementsystem.room.database.InventoryDatabase;
import com.example.inventarymanagementsystem.room.entities.Catagory;
import com.example.inventarymanagementsystem.room.entities.User;
import com.example.inventarymanagementsystem.room.entities.UserHistory;


import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class InventoryRepo {
    private InventoryDatabase inventoryDatabase;

    public InventoryRepo(Context context) {
        this.inventoryDatabase = InventoryDatabase.getInstance(context);
    }

    //******************** observable for database operations ************//
    public PublishSubject<User> userSignInObserver = PublishSubject.create();
    public PublishSubject<User> userLoggedObserver = PublishSubject.create();
    public PublishSubject<String> usererrorObserver = PublishSubject.create();
    public PublishSubject<String> catageryObserver = PublishSubject.create();
    public PublishSubject<Integer> catageryUpdateObserver = PublishSubject.create();
    public PublishSubject<Boolean> updateUserHistory = PublishSubject.create();
    public PublishSubject<List<Catagory>> catageryListObserver = PublishSubject.create();
    public PublishSubject<List<UserHistory>> userHistoryListObserver = PublishSubject.create();


    //********************* user table Operations ************************//
    //registration
    public void insertSigninUser(User user){
        inventoryDatabase.userDao().insertUser(user).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                userSignInObserver.onNext(user);
            }

            @Override
            public void onError(Throwable e) {
                usererrorObserver.onNext(e.getMessage());
            }
        });

    }

    //login
    public void loginUser(String phno,String password){
        inventoryDatabase.userDao().getUser(phno,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        usererrorObserver.onNext(throwable.getMessage());
                        return null;
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        userLoggedObserver.onNext(user);
                    }
                });
    }

    //catagory
    public void addCatagery(Catagory catagory){
        inventoryDatabase.catagoryDao().insertCatagory(catagory).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                catageryObserver.onNext("catagery added "+catagory.getCatagoryName());
            }

            @Override
            public void onError(Throwable e) {
                catageryObserver.onNext(e.getMessage());
            }
        });
    }

    //catagery list
    public void getCatagery(){
        inventoryDatabase.catagoryDao().getCatagories().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Catagory>>() {
            @Override
            public void accept(List<Catagory> catagories) throws Exception {
                catageryListObserver.onNext(catagories);
            }
        });
    }

    //update catagery list
    public void updateCatagory(Catagory catagery){
        inventoryDatabase.catagoryDao().updateCatagory(catagery).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                catageryUpdateObserver.onNext(integer);
            }
        });
    }

    public void addHistory(UserHistory userHistory){
        Log.d("==updateeee",userHistory.getCustomerName());
        inventoryDatabase.userHistoryDao().insertUserHistory(userHistory).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                getHistory(userHistory.getUserPhno());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getHistory(String userPhno){
        inventoryDatabase.userHistoryDao().getUserHistory(userPhno).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UserHistory>>() {
            @Override
            public void accept(List<UserHistory> userHistories) throws Exception {
                Log.d("==>",""+userHistories);
                userHistoryListObserver.onNext(userHistories);
            }
        });
    }

    public void deleteUserHistory(){
        inventoryDatabase.userHistoryDao().truncateTable().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d("==>","deleted succesfully!");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
    public void updateUserHistory(UserHistory userHistory){
        inventoryDatabase.userHistoryDao().updateUserHistory(userHistory).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                updateUserHistory.onNext(true);
                getHistory(userHistory.getUserPhno());
            }
        });
    }



}
