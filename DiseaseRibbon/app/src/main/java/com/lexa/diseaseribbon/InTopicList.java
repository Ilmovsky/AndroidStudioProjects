package com.lexa.diseaseribbon;

import org.w3c.dom.Text;

/**
 * Created by Lexa on 06.04.2016.
 */
public class InTopicList {

    private String ownerId;
    private String subject;
    private String topic;
    private String userName;
    private String photoUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public InTopicList(String topic, String subject, String ownerId, String userName, String photoUrl) {
        this.topic = topic;
        this.subject = subject;
        this.ownerId = ownerId;
        this.userName = userName;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "InTopicList{" +
                "ownerId='" + ownerId + '\'' +
                ", subject='" + subject + '\'' +
                ", topic='" + topic + '\'' +
                ", userName='" + userName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
