package com.example.lexa.entrytothedoctor;

import java.util.ArrayList;

/**
 * Created by Lexa on 21.03.2016.
 */
public class DoctorBase {
    private long offset;
    private ArrayList <Doctor> data = new ArrayList<>();
    private String nextPage;
    private int totalObjects;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public ArrayList<Doctor> getData() {
        return data;
    }

    public void setData(ArrayList<Doctor> data) {
        this.data = data;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

    public DoctorBase() {
        this.offset = offset;
        this.data = data;
        this.nextPage = nextPage;
        this.totalObjects = totalObjects;
    }

    @Override
    public String toString() {
        return "DoctorBase{" +
                "offset=" + offset +
                ", data=" + data +
                ", nextPage='" + nextPage + '\'' +
                ", totalObjects=" + totalObjects +
                '}';
    }
}
