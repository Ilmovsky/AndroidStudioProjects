package com.example.lexa.myapplication;

import java.util.Date;

public class Beer {
    private int id;
    private String name;
    private String country;
    private Date year;
    private int price;
    private boolean visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Beer(int id, boolean visible, int price, Date year, String country, String name) {
        this.id = id;
        this.visible = visible;
        this.price = price;
        this.year = year;
        this.country = country;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", visible=" + visible +
                '}';
    }
}
