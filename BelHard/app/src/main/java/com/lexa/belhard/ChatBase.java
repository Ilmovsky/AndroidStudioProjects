package com.lexa.belhard;

/**
 * Created by Lexa on 25.05.2016.
 */
public class ChatBase {

    private String Text;
    private String Date;
    private String photoUrl;


    public ChatBase() {
    }

    public ChatBase(String text, String date, String photoUrl) {
        Text = text;
        Date = date;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


}
