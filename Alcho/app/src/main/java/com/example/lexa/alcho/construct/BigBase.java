package com.example.lexa.alcho.construct;

/**
 * Created by Lexa on 27.12.2015.
 */
public class BigBase {
    private long id;
    private String date;
    private String name;

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "id= " + id + '\n' +
                "date= " + date + '\n' +
                "name= " + name;
    }
}
