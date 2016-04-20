package com.lexa.diseaseribbon;

import java.util.ArrayList;

/**
 * Created by Lexa on 29.03.2016.
 */
public class DiseaseBase{
private long offset;
private ArrayList<DiseaseList> data = new ArrayList<DiseaseList>();
private String nextPage;
private int totalObjects;

        public long getOffset() {
            return offset;
        }

        public void setOffset(long offset) {
            this.offset = offset;
        }

        public ArrayList<DiseaseList> getData() {
            return data;
        }

        public void setData(ArrayList<DiseaseList> data) {
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

    public DiseaseBase(long offset, ArrayList<DiseaseList> data, String nextPage, int totalObjects) {
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
