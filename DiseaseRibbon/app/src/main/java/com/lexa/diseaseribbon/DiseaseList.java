package com.lexa.diseaseribbon;

import android.widget.EditText;

/**
 * Created by Lexa on 29.03.2016.
 */
public class DiseaseList {

    private String ownerId;
    private String disease;
    private String topic;
    private String tableName;


    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public DiseaseList(String tableName, String topic, String disease, String ownerId) {
        this.tableName = tableName;
        this.topic = topic;
        this.disease = disease;
        this.ownerId = ownerId;
    }


    @Override
    public String toString() {
        return "DiseaseList{" +
                "ownerId='" + ownerId + '\'' +
                ", disease='" + disease + '\'' +
                ", topic='" + topic + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
