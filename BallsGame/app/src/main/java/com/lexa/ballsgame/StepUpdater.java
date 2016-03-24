package com.lexa.ballsgame;

import java.util.TimerTask;

/**
 * Created by Lexa on 29.01.2016.
 */
public class StepUpdater extends TimerTask {

    GameActivity act;

    StepUpdater(GameActivity s){
        this.act = s;
    }

    @Override
    public void run() {
        act.Step();
    }
}
