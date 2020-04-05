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

    @ColumnInfo(name = "CustomerName")
    private String customerName;

    @ColumnInfo(name = "ProductName")
    private String productName;

    @ColumnInfo(name = "ProductQuantity")
    private String productQuantity;

    @ColumnInfo(name = "ProductPrice")
    private String productPrice;

    @ColumnInfo(name = "catagory")
    private String catagory;

    @ColumnInfo(name = "ProductId")
    private String productId;

    @ColumnInfo(name = "catagoryId")
    private String catagoryId;

    @ColumnInfo(name = "TotalPrice")
    private String totalPrice;

    @ColumnInfo(name = "manufactureDate")
    private OffsetDateTime manufactureDate;

    @ColumnInfo(name = "expireDate")
    private OffsetDateTime expireDate;


    public UserHistory() {
    }

    public UserHistory(int sno, String userPhno, OffsetDateTime billDate, String customerName, String productName, String productQuantity, String productPrice, String catagory, String productId, String catagoryId, String totalPrice, OffsetDateTime manufactureDate, OffsetDateTime expireDate) {
        this.sno = sno;
        this.userPhno = userPhno;
        this.billDate = billDate;
        this.customerName = customerName;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.catagory = catagory;
        this.productId = productId;
        this.catagoryId = catagoryId;
        this.totalPrice = totalPrice;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

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

    public OffsetDateTime getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(OffsetDateTime manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public OffsetDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(OffsetDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "UserHistory{" +
            "sno=" + sno +
            ", userPhno='" + userPhno + '\'' +
            ", billDate=" + billDate +
            ", productName='" + productName + '\'' +
            ", productQuantity='" + productQuantity + '\'' +
            ", productPrice='" + productPrice + '\'' +
            ", catagory='" + catagory + '\'' +
            ", productId='" + productId + '\'' +
            ", catagoryId='" + catagoryId + '\'' +
            ", totalPrice='" + totalPrice + '\'' +
            ", manufactureDate=" + manufactureDate +
            ", expireDate=" + expireDate +
            '}';
    }
}
