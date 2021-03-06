package com.example.inventarymanagementsystem.ui.models;

import java.io.Serializable;
import java.util.List;

public class PastHistory implements Serializable {
    String date;
    String name;
    List<PastHistoryProductItem> pastHistoryList;

    public PastHistory(String date, String name, List<PastHistoryProductItem> pastHistoryList) {
        this.date = date;
        this.name = name;
        this.pastHistoryList = pastHistoryList;
    }

    public List<PastHistoryProductItem> getPastHistoryList() {
        return pastHistoryList;
    }

    public void setPastHistoryList(List<PastHistoryProductItem> pastHistoryList) {
        this.pastHistoryList = pastHistoryList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PastHistory{" +
            "date='" + date + '\'' +
            ", name='" + name + '\'' +
            ", pastHistoryList=" + pastHistoryList +
            '}';
    }
}
