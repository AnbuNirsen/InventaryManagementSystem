package com.example.inventarymanagementsystem.ui.models;

import org.threeten.bp.OffsetDateTime;

public class ProductIItem {
    private String productName;
    private String quantity;
    private String price;
    private String total;
    private String catagory;
    private String productId;
    private OffsetDateTime manufactureDate;
    private OffsetDateTime expireDate;
    private String catagoryId;
    private int sno;

    public ProductIItem(String productName, String quantity, String price, String total, String catagory, String productId, OffsetDateTime manufactureDate, OffsetDateTime expireDate, String catagoryId, int sno) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.catagory = catagory;
        this.productId = productId;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
        this.catagoryId = catagoryId;
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }
}
