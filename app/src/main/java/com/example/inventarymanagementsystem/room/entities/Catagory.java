package com.example.inventarymanagementsystem.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.OffsetDateTime;

@Entity(tableName = "Catagories")
public class Catagory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int catagoryId;

    @ColumnInfo(name = "ProductName")
    private String productName;

    @ColumnInfo(name = "ProductId")
    private String productId;

    @ColumnInfo(name = "ProductPrice")
    private String productPrice;

    @ColumnInfo(name = "CatagoryName")
    private String catagoryName;

    @ColumnInfo(name = "QntyType")
    private String qntyType;

    @ColumnInfo(name="ProductExpDate")
    private OffsetDateTime prodctExpDate;

    @ColumnInfo(name = "ProductManufactureDate")
    private OffsetDateTime productManufactureDate;


    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getQntyType() {
        return qntyType;
    }

    public void setQntyType(String qntyType) {
        this.qntyType = qntyType;
    }

    public OffsetDateTime getProdctExpDate() {
        return prodctExpDate;
    }

    public void setProdctExpDate(OffsetDateTime prodctExpDate) {
        this.prodctExpDate = prodctExpDate;
    }

    public OffsetDateTime getProductManufactureDate() {
        return productManufactureDate;
    }

    public void setProductManufactureDate(OffsetDateTime productManufactureDate) {
        this.productManufactureDate = productManufactureDate;
    }
}
