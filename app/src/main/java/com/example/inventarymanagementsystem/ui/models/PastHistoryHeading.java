package com.example.inventarymanagementsystem.ui.models;

import org.threeten.bp.OffsetDateTime;

public class PastHistoryHeading {
    private String billDate;
    private String name;

    public PastHistoryHeading(String billDate, String name) {
        this.billDate = billDate;
        this.name = name;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
