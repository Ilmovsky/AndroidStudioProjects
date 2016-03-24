package com.lexa.ballsgame;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.TimerTask;

/**
 * Created by Lexa on 29.01.2016.
 */
public class GraphUpdater extends TimerTask {

    GameSurface surf;

    GraphUpdater(GameSurface surf){
        this.surf = surf;
    }

    @Override
    public void run() {
        Canvas c = null;
        try{
        c = surf.getHolder().lockCanvas();}
        finally {
            synchronized (surf){
                if (c != null) {
                    c.drawColor(Color.BLACK);
                    surf.drawSnake(c);
                    surf.getHolder().unlockCanvasAndPost(c);
                }}
        }
    }
    }

