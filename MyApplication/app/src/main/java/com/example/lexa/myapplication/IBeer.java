package com.example.lexa.myapplication;

import java.util.ArrayList;

public class IBeer {
    private String name;
    private String location;
    ArrayList <String> emails;
    ArrayList <Beer> goods = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public ArrayList<String> getEmails() {
        return emails;
    }
    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
    public ArrayList<Beer> getGoods() {
        return goods;
    }
    public void setGoods(ArrayList<Beer> goods) {
        this.goods = goods;
    }
    public IBeer() {
        super();
        this.name = name;
        this.location = location;
        this.emails = emails;
        this.goods = goods;
    }
}
