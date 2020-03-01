package com.example.inventarymanagementsystem.ui.models;

public class ProductIItem {
    private String productName;
    private String quantity;
    private String price;
    private String total;
    private String catagory;
    private String productId;
    private String manufactureDate;
    private String expireDate;
    private String catagoryId;

    public ProductIItem(String productName, String quantity, String price, String total, String catagory, String productId, String manufactureDate, String expireDate, String catagoryId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.catagory = catagory;
        this.productId = productId;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
        this.catagoryId = catagoryId;
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

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }
}
