package com.lexa.newnewstytby.ormLite;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lexa on 09.05.2016.
 */

@DatabaseTable(tableName = "tutnews")
public class BaseClass implements Parcelable {

    public final static String GOAL_NAME_FIELD_NAME = "name";
    public final static String GOAL_NAME_FIELD_LINK = "link";
    public final static String GOAL_NAME_FIELD_PHOTO = "photo";

        @DatabaseField(generatedId = true)
        private Long id;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = GOAL_NAME_FIELD_NAME)
    private String name;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = GOAL_NAME_FIELD_LINK)
    private String link;
    @DatabaseField(dataType = DataType.STRING)
    private String date;
    @DatabaseField(dataType = DataType.STRING, columnName = GOAL_NAME_FIELD_PHOTO)
    private String photo;

    public BaseClass(String name, String link, String date, String photo) {
        this.name = name;
        this.link = link;
        this.date = date;
        this.photo = photo;
    }

    public BaseClass(Parcel in) {

        this.name = in.readString();
        this.link = in.readString();
        this.date = in.readString();
        this.photo = in.readString();
    }

    public BaseClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(date);
        dest.writeString(photo);

    }

    public static final Parcelable.Creator<BaseClass> CREATOR = new Parcelable.Creator<BaseClass>() {

        @Override
        public BaseClass[] newArray(int size) {
            return new BaseClass[size];
        }

        @Override
        public BaseClass createFromParcel(Parcel source) {
            return new BaseClass(source);
        }
    };
}
