package com.lexa.tripworld.parseObject;

import com.lexa.tripworld.parseObject.CityLocation;

/**
 * Created by Lexa on 20.05.2016.
 */
public class ListOfCities {
    private String name;
    private String country;
    private CityLocation geo_position;
    private double distance;

    public ListOfCities(String country, CityLocation geo_position, double distance, String name) {
        this.country = country;
        this.geo_position = geo_position;
        this.distance = distance;
        this.name = name;
    }

    public ListOfCities() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public CityLocation getGeoPosition() {
        return geo_position;
    }

    public void setGeoPosition(CityLocation geo_position) {
        this.geo_position = geo_position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " " + country ;
    }
}
