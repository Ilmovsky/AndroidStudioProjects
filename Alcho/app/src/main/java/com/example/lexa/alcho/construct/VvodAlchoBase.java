package com.example.lexa.alcho.construct;

/**
 * Created by Lexa on 27.12.2015.
 */
public class VvodAlchoBase {

    private String name;
    private String name1;
    private double degrees;
    private  int colich;
    private long id;
    private long idAll;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getName1() {
        return name1;
    }

    public double getDegrees() {
        return degrees;
    }

    public int getColich() {
        return colich;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public void setColich(int colich) {
        this.colich = colich;
    }

    public long getIdAll() {
        return idAll;
    }

    public void setIdAll(long idAll) {
        this.idAll = idAll;
    }

    public VvodAlchoBase() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VvodAlchoBase)) return false;

        VvodAlchoBase that = (VvodAlchoBase) o;

        if (Double.compare(that.getDegrees(), getDegrees()) != 0) return false;
        if (getColich() != that.getColich()) return false;
        if (getId() != that.getId()) return false;
        if (!getName().equals(that.getName())) return false;
        return getName1().equals(that.getName1());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        result = 31 * result + getName1().hashCode();
        temp = Double.doubleToLongBits(getDegrees());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getColich();
        result = 31 * result + (int) (getId() ^ (getId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "name= " + name + '\n' +
                "name1= " + name1 + '\n' +
                "degrees= " + degrees + '\n' +
                "colich= " + colich + '\n' +
                "idAll= " + idAll;
    }
}
