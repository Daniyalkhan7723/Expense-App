package com.sis.expensiveaapp.Model;

import java.io.Serializable;
import java.util.Date;

public class Pojo implements Serializable {
    private int amount;
    private int id;
    private String description;
    private int price;
    private String Title;
    private String date;


    public Pojo(int amount, String description, String date) {
        this.amount = amount;
        this.description = description;
        this.date=date;
    }

    public Pojo(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Pojo(int amount, String description, String title, String date) {
        this.amount = amount;
        this.description = description;
        Title = title;
        this.date = date;
    }

    public Pojo(int amount, int id, String description) {
        this.amount = amount;
        this.id = id;
        this.description = description;

    }

    public Pojo(int amount, int id, String description, String date) {
        this.amount = amount;
        this.id = id;
        this.description = description;
        this.date = date;
    }
    //    public Pojo(String description, String title) {
//        this.description = description;
//        Title = title;
//    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

