package com.lexa.tripworld.parseObject;

/**
 * Created by Lexa on 20.05.2016.
 */
public class CityLocation {
    private double latitude;
    private double longitude;

    public CityLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CityLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
