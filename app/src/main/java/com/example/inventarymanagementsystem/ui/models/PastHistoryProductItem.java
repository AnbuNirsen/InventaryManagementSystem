package com.example.inventarymanagementsystem.ui.models;

public class PastHistoryProductItem{
        String productName;
        String productQnty;
        String productPrice;
        String productId;
        String qntyType;
        int totalAmount;

        public PastHistoryProductItem(String productName, String productQnty, String productPrice, String productId,String qntyType, int totalAmount) {
            this.productName = productName;
            this.productQnty = productQnty;
            this.productPrice = productPrice;
            this.productId = productId;
            this.totalAmount = totalAmount;
            this.qntyType = qntyType;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductQnty() {
            return productQnty;
        }

        public void setProductQnty(String productQnty) {
            this.productQnty = productQnty;
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

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getQntyType() {
            return qntyType;
        }

        public void setQntyType(String qntyType) {
            this.qntyType = qntyType;
        }
    }
