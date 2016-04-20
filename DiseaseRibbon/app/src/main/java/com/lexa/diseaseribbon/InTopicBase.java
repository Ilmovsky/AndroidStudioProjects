package com.lexa.diseaseribbon;

import java.util.ArrayList;

/**
 * Created by Lexa on 06.04.2016.
 */
public class InTopicBase {
    private long offset;
    private ArrayList<InTopicList> data = new ArrayList<InTopicList>();
    private String nextPage;
    private int totalObjects;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public ArrayList<InTopicList> getData() {
        return data;
    }

    public void setData(ArrayList<InTopicList> data) {
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

    public InTopicBase(long offset, ArrayList<InTopicList> data, String nextPage, int totalObjects) {
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
