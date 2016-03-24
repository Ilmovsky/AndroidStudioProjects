package com.lexa.ballsgame;

/**
 * Created by Lexa on 23.03.2016.
 */
public class Speeds {

    private int time;
    private String speed;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void addNew(int time, String speed){
        setTime(time);
        setSpeed(speed);
    }
}
