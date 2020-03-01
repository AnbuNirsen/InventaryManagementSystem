package com.example.inventarymanagementsystem.room.repository;

import android.content.Context;

import com.example.inventarymanagementsystem.room.database.InventoryDatabase;
import com.example.inventarymanagementsystem.room.entities.User;


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


    //********************* user table Operations ************************//
    //registration
    public void insertSigninUser(User user){
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                inventoryDatabase.userDao().insertUser(user);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                userSignInObserver.onNext(user);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                usererrorObserver.onNext(e.getMessage());
//            }
//        });
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


}
