package com.lexa.ballsgame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Lexa on 29.01.2016.
 */
public class SnakeGame {

    // класс определяющий позицию
    public class pos {
        int x;
        int y;

        //конструктор
        pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Константы направления
    public static final int DIR_NORTH = 1;
    public static final int DIR_EAST = 2;
    public static final int DIR_SOUTH = 3;
    public static final int DIR_WEST = 4;

    // ширина и высота игрового поля
    // подбиралось исходя из пропорций экрана моего девайса
    public static int mFieldX = 17;
    public static int mFieldY = 30;

    // Очки в игре
    public int mScore=0;

    // Матрица - игровое поле
    private int mField[][] = new int[mFieldX][mFieldY];

    // Сама змея - массив двумерных координат каждого сегмента
    private ArrayList<pos> mSnake = new ArrayList<pos>();

    private ArrayList<pos> mPregr = new ArrayList<pos>();

    private ArrayList<pos> mPregr2 = new ArrayList<pos>();

    // Текущее напраление движения змеи
    volatile int mDirection = DIR_SOUTH;
    static int mDir = DIR_SOUTH;

    // пераметр по которому определяется должна ли
    // змейка расти или нет смотрите ниже там понятно


    // Собственно конструктор
    SnakeGame() {
        // очищаем игровое поле
        for (int i = 0; i < mFieldX; i++)
            for (int j = 0; j < mFieldY; j++) {
                mField[i][j] = 0;
            }
        // создаем змею
        mSnake.add(new pos(10, 15));
        // каждая клетка поля в которой
        // находится змея - отмечается -1
        mField[10][15] = -1;

        mPregr.add(new pos(4,6));
        mField[4][6] = 1;
        mPregr.add(new pos(4,7));
        mField[4][7] = 1;
        mPregr.add(new pos(4,8));
        mField[4][8] = 1;
        mPregr.add(new pos(13,23));
        mField[13][23] = 1;
        mPregr.add(new pos(13,22));
        mField[13][22] = 1;
        mPregr.add(new pos(13,21));
        mField[13][21] = 1;
        mPregr.add(new pos(13,20));
        mField[13][20] = 1;
        mPregr.add(new pos(10,2));
        mField[11][2] = 1;

        mPregr2.add(new pos(7,1));
        mField[7][1] = 1;
        mPregr2.add(new pos(8,1));
        mField[8][1] = 1;
        mPregr2.add(new pos(9,1));
        mField[9][1] = 1;


        // добавляем на игровое поле фрукт
        addFruite();
    }

    // метод добавляет фрукт на игровое поле
    // поскольку игра у нас самая простая то
    // и фрукт только один а его код на поле - 2
    private void addFruite() {
        boolean par = false;
        while (!par) {
            int x = (int) (Math.random() * mFieldX);
            int y = (int) (Math.random() * mFieldY);
            if (mField[x][y] == 0) {
                mField[x][y] = 2;
                par = true;
            }
        }
    }


    public void moove(){
        switch (mDir) {
            case DIR_SOUTH: {
                int nextY = mPregr2.get(mPregr2.size() - 1).y + 1;
                int nextX = mPregr2.get(mPregr2.size() - mPregr2.size()).x;
                if ((nextY < mFieldY) && mField[nextX][nextY] != 2 && mField[nextX+1][nextY] != 2 && mField[nextX+2][nextY] != 2) {
                    for (int i = 0; i < mPregr2.size(); i++) {
                        mField[mPregr2.get(0).x][mPregr2.get(0).y] = 0;
                        mPregr2.remove(0);
                        mPregr2.add(new pos(nextX+i, nextY));
                        mField[mPregr2.get(i).x][nextY] = 1;
                    }
                } else {
                        for (int i = 0; i < mPregr2.size(); i++) {
                            mField[mPregr2.get(0).x][mPregr2.get(0).y] = 0;
                            mPregr2.remove(0);
                            mPregr2.add(new pos(nextX + i, 1));
                            mField[nextX + i][1] = 1;
                        }
                }
            }
            }
    }

    public boolean veryTy(){
        for (int i = 0; i < mSnake.size(); i++) {
            if (mField[mSnake.get(i).x][mSnake.get(i).y] == 1) {
                return false;
            }
            else {
                return true;
            }
        }
        return true;
    }

    // Этот метод соержит в себе всю логику игры
    // здесь опиываются все действия которые должны происходить
    // при каждом перемещении змеи
    // при этом, учитывается текущее направление и
    // проверяется, может ли змея ходить в указанном направлении
    // собственно вся игровая логика заключена в этом методе
    public boolean nextMove() {
        moove();
        for (int i = 0; i < mSnake.size(); i++) {
            if (mField[mSnake.get(i).x][mSnake.get(i).y] == 1) {
                return false;
            }
        }
        // смотрим, куда у нас направлена змея сейчас
        switch (mDirection) {
            // если на север
            case DIR_NORTH: {
                // тогда рассчитываем координаты в которые попадет
                // голова змеи на следуюзщем ходу
                int nextX = mSnake.get(mSnake.size() - 1).x;
                int nextY = mSnake.get(mSnake.size() - 1).y - 1;
                // если мы не утыкаемся в верхнюю стенку
                // и если клетка куда мы идем пуста (о чем нам говорит
                // нулевое значение в указанной клетке поля)
                if ((nextY >= 0) && mField[nextX][nextY] == 0) {
                    // то мы проверяем, растет ли в данный момент змея

                        // если не растет, то передвигаем хвост змеи
                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                    //Затем перемещаем голову змеи
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    veryTy();
                    // и на этом все закончилось <img src="http://davidmd.ru/wp-includes/images/smilies/simple-smile.png" alt=":)" class="wp-smiley" style="height: 1em; max-height: 1em;"> возвращаем истину
                    return true;
                } else if ((nextY >= 0) && mField[nextX][nextY] == 1) {
                    // если мы уткнулись в препятствие возвращаем ложь

                    return false;
                } else if ((nextY >= 0) && mField[nextX][nextY] > 1) {
                    // А вот если мы уткнулись во фрукт,
                    // тогда увеличиваем запас роста

                    // добавляем очков!
                    mScore=mScore+1;
                    // и переносим голову змеи
                    // на соответствующую клетку поля
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    // ну и соответственно добавляем на поле новый фрукт!
                    addFruite();
                    veryTy();
                    return true;
                } else  if ((nextY >=0) && mField[nextX][nextY] == -1) {
                    int nextX1 = mSnake.get(mSnake.size() - 1).x;
                    int nextY1 = mSnake.get(mSnake.size() - 1).y+1;
                    if ((nextY1 < mFieldY) && mField[nextX1][nextY1] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        return true;
                    } else if ((nextY1 < mFieldY) && mField[nextX1][nextY1] > 1) {

                        mScore=mScore+1;
                        mField[nextX1][nextY1] = 0;
                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        addFruite();
                        return true;}
                }
                else{
                    return false;
                }
            }
            // Здесь все то же самое, только
            // для других направлений
            case DIR_EAST: {
                int nextX = mSnake.get(mSnake.size() - 1).x + 1;
                int nextY = mSnake.get(mSnake.size() - 1).y;
                if ((nextX < mFieldX) && mField[nextX][nextY] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    veryTy();
                    return true;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] > 1) {

                    mScore=mScore+1;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    veryTy();
                    return true;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] == -1) {
                    int nextX1 = mSnake.get(mSnake.size() - 1).x - 1;
                    int nextY1 = mSnake.get(mSnake.size() - 1).y;
                    if ((nextX1 >=0) && mField[nextX1][nextY1] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        return true;
                    } else if ((nextX1 >=0) && mField[nextX1][nextY1] > 1) {

                        mScore=mScore+1;
                        mField[nextX1][nextY1] = 0;
                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        addFruite();
                        return true;}
                }
                else{
                    return false;
                }
            }
            case DIR_SOUTH: {
                int nextX = mSnake.get(mSnake.size() - 1).x;
                int nextY = mSnake.get(mSnake.size() - 1).y + 1;
                if ((nextY < mFieldY) && mField[nextX][nextY] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    veryTy();
                    return true;
                } else if ((nextY < mFieldY) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextY < mFieldY) && mField[nextX][nextY] > 1) {

                    mScore=mScore+1;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    veryTy();
                    return true;
                } else  if ((nextY < mFieldY) && mField[nextX][nextY] == -1) {
                    int nextX1 = mSnake.get(mSnake.size() - 1).x;
                    int nextY1 = mSnake.get(mSnake.size() - 1).y-1;
                    if ((nextY1 > 0) && mField[nextX1][nextY1] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        return true;
                    } else if ((nextY1 >0) && mField[nextX1][nextY1] > 1) {

                        mScore=mScore+1;
                        mField[nextX1][nextY1] = 0;
                        mSnake.add(new pos(nextX1, nextY1));
                        mField[nextX1][nextY1] = -1;
                        addFruite();
                        return true;}
                }
                else{
                    return false;
                }
            }
            case DIR_WEST: {
                int nextX = mSnake.get(mSnake.size() - 1).x - 1;
                int nextY = mSnake.get(mSnake.size() - 1).y;
                if ((nextX >= 0) && mField[nextX][nextY] == 0) {

                        mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                        mSnake.remove(0);

                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    veryTy();
                    return true;
                } else if ((nextX >= 0) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX >= 0) && mField[nextX][nextY] > 1) {

                    mScore=mScore+1;
                    mField[nextX][nextY] = 0;
                    mSnake.add(new pos(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    addFruite();
                    veryTy();
                    return true;
                } else  if ((nextX >=0) && mField[nextX][nextY] == -1) {
                        int nextX1 = mSnake.get(mSnake.size() - 1).x + 1;
                        int nextY1 = mSnake.get(mSnake.size() - 1).y;
                        if ((nextX1 < mFieldX) && mField[nextX1][nextY1] == 0) {

                            mField[mSnake.get(0).x][mSnake.get(0).y] = 0;
                            mSnake.remove(0);

                            mSnake.add(new pos(nextX1, nextY1));
                            mField[nextX1][nextY1] = -1;
                            return true;
                        } else if ((nextX1 < mFieldX) && mField[nextX1][nextY1] > 1) {

                            mScore=mScore+1;
                            mField[nextX1][nextY1] = 0;
                            mSnake.add(new pos(nextX1, nextY1));
                            mField[nextX1][nextY1] = -1;
                            addFruite();
                            return true;}
                    }
                    else{
                        return false;
                    }
                }
            }

        return false;
    }

    // здесь и нижу всякие геттеры и сеттеры
    // думаю тут и объяснять нечего
    public int getDirection() {
        return mDirection;
    }

    public void clearScore(){
        this.mScore=0;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public void setmDir(int mDir) {
        this.mDir = mDir;
    }

    public int[][] getmField() {
        return mField;
    }

    public int getSnakeLength() {
        return  mSnake.size();
    }

    public ArrayList<pos> getmSnake() {
        return mSnake;
    }

}
