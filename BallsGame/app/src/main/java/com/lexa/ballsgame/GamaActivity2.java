package com.lexa.ballsgame;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;

/**
 * Created by Lexa on 23.03.2016.
 */
public class GamaActivity2  extends Activity implements View.OnTouchListener {

    GameSurface surf;
    Timer t;
    static SingleTon manager = SingleTon.getInstance();
    int speed = 0;


    float SSX = 0, SSY = 0;
    float SX = 0, SY = 0;


    // Ну тут обрабатываем создание активити
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surf = new GameSurface(this);
        this.setContentView(surf);
        t = new Timer();
        speed = manager.getSpeeder();
        Log.e("cxvcxv", String.valueOf(speed));

    }

    @Override
    public void onStart() {
        super.onStart();
        t.schedule(new GraphUpdater(surf), 0, speed);
        // Запускаем таймер обновления положения змейки
        t.schedule(new StepUpdater2(this), 0, speed);

        surf.setSomeText("Your score is: " + MainActivity.GAME_SCORE);
    }


    // Обрабатываем остановку активити
    @Override
    public void onStop() {
        super.onStop();
        // Останавливаем таймеры
        t.cancel();
        t.purge();

    }

    private synchronized int getDirection(float x, float y) {
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) {

                return SnakeGame.DIR_WEST;
            } else {

                return SnakeGame.DIR_EAST;
            }
        } else {
            if (y > 0) {

                return SnakeGame.DIR_NORTH;
            } else {

                return SnakeGame.DIR_SOUTH;
            }
        }
    }


    public void Step() {
        // Если ход не удался то закрываем текущую активити
        if (!surf.mField.nextMove()) {
            MainActivity.GAME_MODE=1;
            this.finish();
        }
        // Если все впорядке то обновляем очки
        // в стартовой активити
        else{
            MainActivity.GAME_SCORE=this.surf.mField.mScore;
            if (surf.mField.mScore % 2 == 0){
                speed = speed/2;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                SX = x;
                SY = y;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP: // отпускание
                SSX = x;
                SSY = y;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        float dirX = SX - SSX;
        float dirY = SY - SSY;
        // Устанавливаем для змеи новое направление
        int a = getDirection(dirX, dirY);
        surf.mField.setDirection(a);

        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    }

