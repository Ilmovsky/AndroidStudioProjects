package com.lexa.mymaps;

/**
 * Created by Lexa on 02.02.2016.
 */
public class MapBase {
    private long id;
    private String country;
    private String kind;
    private String place;
    private String inform;
    private double coordX;
    private double coordY;

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getKind() {
        return kind;
    }

    public String getPlace() {
        return place;
    }

    public String getInform() {
        return inform;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapBase)) return false;

        MapBase mapBase = (MapBase) o;

        if (getId() != mapBase.getId()) return false;
        if (Double.compare(mapBase.getCoordX(), getCoordX()) != 0) return false;
        if (Double.compare(mapBase.getCoordY(), getCoordY()) != 0) return false;
        if (!getCountry().equals(mapBase.getCountry())) return false;
        if (!getKind().equals(mapBase.getKind())) return false;
        if (!getPlace().equals(mapBase.getPlace())) return false;
        return getInform().equals(mapBase.getInform());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getKind().hashCode();
        result = 31 * result + getPlace().hashCode();
        result = 31 * result + getInform().hashCode();
        temp = Double.doubleToLongBits(getCoordX());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCoordY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
