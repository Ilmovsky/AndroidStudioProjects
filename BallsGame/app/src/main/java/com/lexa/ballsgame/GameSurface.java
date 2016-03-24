package com.lexa.ballsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by Lexa on 29.01.2016.
 */
public class GameSurface extends SurfaceView {

    SnakeGame mField;

    Bitmap mHead, mBg, mFruite;

    String someText = "123";

    float x, y;

    // Установка новых кординат телефона в пространстве
    // для того чтобы правильно нарисовать кружки на фоне
//    public void setXY(float x, float y) {
//        this.x = x;
//        this.y = y;
//    }

    // Собственно конструктор в котором мы загружаем
    // из ресурсов битмапы и добавляем метод обратного вызова
    // для нашей Surface
    public GameSurface(Context context) {
        super(context);

        mField = new SnakeGame();
        mHead = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ksnake);
        mBg = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.pr);
        mFruite = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.eat);
    }

    // метод в котором устанавливаем текст
    public void setSomeText(String someText) {
        this.someText = someText;
    }

    // Рисуем здесь
    void drawSnake(Canvas c) {
        int width = c.getWidth();
        int height = c.getHeight();
        int mx = width / SnakeGame.mFieldX;
        int my = height / SnakeGame.mFieldY;
        // стрейчим битмапы
        Bitmap head = Bitmap.createScaledBitmap(mHead, mx, my, true);
        Bitmap bg = Bitmap.createScaledBitmap(mBg, mx, my, true);
        // создаем на всякий кисточку
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        Bitmap fruite = Bitmap.createScaledBitmap(mFruite, mx, my, true);
        // рисуем игровое поле с фруктами на нем
        for (int i = 0; i < SnakeGame.mFieldX; i++) {
            for (int j = 0; j < SnakeGame.mFieldY; j++) {
                if (mField.getmField()[i][j] ==1){
                   c.drawBitmap(bg, mx * i, my * j, paint);
                }
                if (mField.getmField()[i][j] > 1) {
                    c.drawBitmap(fruite, mx * i, my * j, paint);
                }
            }
        }
        paint.setAlpha(0);
        // рисуем змею
        for (int i = 0; i < mField.getSnakeLength(); i++) {

                c.drawBitmap(head, mField.getmSnake().get(i).x * mx, mField
                        .getmSnake().get(i).y * my, new Paint());

        }
        // рисуем текст
        paint.setColor(Color.WHITE);
        paint.setAlpha(255);
        paint.setTextSize(30);
        c.drawText(someText, 50, 50,  paint);
    }
}
