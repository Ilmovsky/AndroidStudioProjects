package com.lexa.ballsgame;

import java.util.TimerTask;

/**
 * Created by Lexa on 23.03.2016.
 */
public class StepUpdater2 extends TimerTask {

    GamaActivity2 act;

    StepUpdater2(GamaActivity2 s){
        this.act = s;
    }

    @Override
    public void run() {
        act.Step();
    }
}
