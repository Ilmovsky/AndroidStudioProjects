package com.lexa.belhard;

/**
 * Created by Lexa on 25.05.2016.
 */
public class MarketBase {

    private int url;
    private String collection;
    private String writer;
    private int prise;

    public MarketBase(int url, String collection, String writer, int prise) {
        this.url = url;
        this.collection = collection;
        this.writer = writer;
        this.prise = prise;
    }

    public MarketBase() {
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }
}
