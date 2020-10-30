package com.sis.expensiveaapp.Model;

import java.io.Serializable;
import java.util.Date;

public class Pojo1 implements Serializable {
    private int amount;
    private int id;
    private String description;
    private String Title;
    private String date;

    public Pojo1(int amount, String description, String title, String date) {
        this.amount = amount;
        this.description = description;
        Title = title;
        this.date = date;
    }

    public Pojo1(int amount, String description, String title) {
        this.amount = amount;
        this.description = description;
        Title = title;
    }

    public Pojo1(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

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
