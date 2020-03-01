package com.example.inventarymanagementsystem.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey
    @NonNull
    private String PhoneNo;

    @ColumnInfo(name = "Name")
    private String UserName;

    @ColumnInfo(name = "Email Id")
    private String UserEmailId;

    @ColumnInfo(name = "Address")
    private String UserAddress;

    @ColumnInfo(name = "Password")
    private String UserPassword;

    public User() {
    }

    public User(@NonNull String phoneNo, String userName, String userEmailId, String userAddress, String userPassword) {
        PhoneNo = phoneNo;
        UserName = userName;
        UserEmailId = userEmailId;
        UserAddress = userAddress;
        UserPassword = userPassword;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmailId() {
        return UserEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        UserEmailId = userEmailId;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
