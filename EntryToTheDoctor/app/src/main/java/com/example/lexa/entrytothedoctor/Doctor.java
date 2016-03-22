package com.example.lexa.entrytothedoctor;

/**
 * Created by Lexa on 21.03.2016.
 */
public class Doctor {
    private String Specialization;
    private long created;
    private String ___class;
    private long ownerId;
    private String Surname;
    private long updated;
    private int Age;
    private String objectId;
    private String Name;
    private String __meta;

    public String get__meta() {
        return __meta;
    }

    public void set__meta(String __meta) {
        this.__meta = __meta;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String get___class() {
        return ___class;
    }

    public void set___class(String ___class) {
        this.___class = ___class;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Specialization='" + Specialization + '\'' +
                ", created=" + created +
                ", ___class='" + ___class + '\'' +
                ", ownerId=" + ownerId +
                ", Surname='" + Surname + '\'' +
                ", updated=" + updated +
                ", Age=" + Age +
                ", objectId='" + objectId + '\'' +
                ", Name='" + Name + '\'' +
                ", meta='" + __meta + '\'' +
                '}';
    }
}
