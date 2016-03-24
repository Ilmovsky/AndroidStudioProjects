package com.lexa.ballsgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    AlertDialog.Builder builder;
    Button butt;
    Button butt2;
    Button butt3;
    TextView tv;
    TextView tv2;

    static SingleTon manager = SingleTon.getInstance();

    // режим запуска активити - 0 первый запуск
    // 1 - запуск активити после проигрыша
    public static int GAME_MODE=0;

    public static int GAME_CONTROL=0;

    public static int GAME_SCORE=0;

    public static String GAME_SPEED = "1";

    public List <Speeds> speedses = new ArrayList<Speeds>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int [] time = getResources().getIntArray(R.array.times);
        String [] speed = getResources().getStringArray(R.array.speeds);
        for(int i =0; i < time.length; i++){
            Speeds speeds = new Speeds();
            speeds.addNew(time[i], speed[i]);
            speedses.add(speeds);
        }
        if(manager.getSpeeder() == 0){
            GAME_SPEED = "1";
            speedInform();
        }
        else{
            speedStart();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        // Вот тут забавный момент с
        // загрузкой разных разметок
        if (GAME_MODE==0){
            setContentView(R.layout.activity_main);
            butt = (Button) this.findViewById(R.id.button);
            butt2 = (Button) this.findViewById(R.id.button2);
            butt3 = (Button) this.findViewById(R.id.button3);

            if (manager.getControl() == 1){
              butt2.setText("Accelerometer");
                GAME_CONTROL = 1;
            }
            else{
                butt2.setText("Hand");
            }

            butt3.setText("Our speed:" + GAME_SPEED);

            butt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSpeed();
                }
            });

            butt.setOnClickListener(this);
        }
        else
        {
            setContentView(R.layout.lose);
            butt = (Button) this.findViewById(R.id.button);
            butt2 = (Button) this.findViewById(R.id.button2);
            butt3 = (Button) this.findViewById(R.id.button3);
            tv = (TextView) this.findViewById(R.id.textView2);
            tv2 = (TextView) this.findViewById(R.id.textView3);
            tv.setText("Your score: "+GAME_SCORE);
            if (GAME_SCORE > manager.getBestScore()){
                tv2.setText("Best score: "+GAME_SCORE);
                manager.setBestScore(GAME_SCORE);
            }
            else {
                tv2.setText("Best score: " + manager.getBestScore());
            }

            if (manager.getControl() == 1){
                butt2.setText("Accelerometer");
                GAME_CONTROL = 1;
            }
            else{
                butt2.setText("Hand");
            }

            butt3.setText("Our speed:" + GAME_SPEED);
            butt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSpeed();
                }
            });

            butt.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        // Для любой разметки если мы нажимем на кнопку, то игра запускается
        GAME_MODE=0;
        GAME_SCORE=0;
        Intent i;
        if(GAME_CONTROL == 1){
            i = new Intent(this, com.lexa.ballsgame.GameActivity.class);
        }
        else {
            i = new Intent(this, com.lexa.ballsgame.GamaActivity2.class);
        }
        this.startActivity(i);
    }

    public void onControl(View v){
        if(GAME_CONTROL == 1){
            butt2.setText("Hand");
            GAME_CONTROL = 0;
            manager.setControl(0);
        }
        else{
            butt2.setText("Accelerometer");
            GAME_CONTROL = 1;
            manager.setControl(1);
        }
    }

    public Dialog onSpeed(){
        final String [] speeding = getResources().getStringArray(R.array.speeds);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Speed:");
        builder.setItems(speeding, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               GAME_SPEED = speeding[which];
               butt3.setText("Our speed:" + GAME_SPEED);
               speedInform();
           }
       });
        builder.setCancelable(true);
        return builder.show();
    }

    public void speedInform(){
        for(int i=0;i<speedses.size();i++){
            if(speedses.get(i).getSpeed().equalsIgnoreCase(GAME_SPEED)){
                manager.setSpeeder(speedses.get(i).getTime());
                break;
            }
        }
    }

    public void speedStart(){
        for(int i=0;i<speedses.size();i++){
            if(speedses.get(i).getTime() == manager.getSpeeder()){
                GAME_SPEED = speedses.get(i).getSpeed();
                break;
            }
        }
    }


}
