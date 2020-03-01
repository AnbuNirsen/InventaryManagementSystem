package com.example.inventarymanagementsystem.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.inventarymanagementsystem.room.converters.DateConverter;

import org.threeten.bp.OffsetDateTime;

@Entity(tableName = "History")
public class UserHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SNO")
    private int sno;

    @ColumnInfo(name = "UserPhnoNo")
    private String userPhno;

    @ColumnInfo(name = "Date")
    private OffsetDateTime billDate;

    @ColumnInfo(name = "ProductName")
    private String productName;

    @ColumnInfo(name = "ProductQuantity")
    private String productQuantity;

    @ColumnInfo(name = "ProductPrice")
    private String productPrice;

    @ColumnInfo(name = "ProductId")
    private String productId;

    @ColumnInfo(name = "TotalPrice")
    private String totalPrice;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getUserPhno() {
        return userPhno;
    }

    public void setUserPhno(String userPhno) {
        this.userPhno = userPhno;
    }

    public OffsetDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(OffsetDateTime billDate) {
        this.billDate = billDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
