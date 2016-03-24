package com.lexa.ballsgame;

/**
 * Created by Lexa on 23.03.2016.
 */
public class SingleTon {

    private int bestScore;
    private int control;
    private int speeder;

    public int getSpeeder() {
        return speeder;
    }

    public void setSpeeder(int speeder) {
        this.speeder = speeder;
    }

    public static void setInstance(SingleTon instance) {
        SingleTon.instance = instance;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    private static SingleTon instance;

    public static SingleTon getInstance() {
        if(instance ==null){
            instance = new SingleTon();
        }
        return instance;
    }

    private SingleTon() {
    }

}
