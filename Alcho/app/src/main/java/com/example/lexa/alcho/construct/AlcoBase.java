package com.example.lexa.alcho.construct;

/**
 * Created by Lexa on 21.12.2015.
 */
public class AlcoBase {

    private long id;
    private String name;
    private String degrees;
    private String site;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSite() {return site;}

    public void setSite(String site) {this.site = site;}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "Название = " + name;
    }
}
