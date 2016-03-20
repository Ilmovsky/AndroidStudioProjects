package com.lexa.mymaps;

/**
 * Created by Lexa on 02.02.2016.
 */
public class MapsBase {

    private long id;
    private String country;
    private String name;
    private double coordX;
    private double coordY;
    private int zoomLevel;

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public MapsBase() {
    }

    public MapsBase(long id, String country, String name, double coordX, double coordY) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapsBase)) return false;

        MapsBase mapsBase = (MapsBase) o;

        if (getId() != mapsBase.getId()) return false;
        if (Double.compare(mapsBase.getCoordX(), getCoordX()) != 0) return false;
        if (Double.compare(mapsBase.getCoordY(), getCoordY()) != 0) return false;
        if (getZoomLevel() != mapsBase.getZoomLevel()) return false;
        if (!getCountry().equals(mapsBase.getCountry())) return false;
        return getName().equals(mapsBase.getName());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getName().hashCode();
        temp = Double.doubleToLongBits(getCoordX());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCoordY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getZoomLevel();
        return result;
    }
}
