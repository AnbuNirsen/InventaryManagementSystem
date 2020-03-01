package com.example.inventarymanagementsystem.room.callbacks;

import com.example.inventarymanagementsystem.room.entities.User;

public interface UserCallBack {
    public void onUserSignedIn(User user);
    public void onUserLoggedIn(User user);
    public void onError(String error);
}
