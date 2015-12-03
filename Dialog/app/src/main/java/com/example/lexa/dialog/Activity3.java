package com.example.lexa.dialog;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Lexa on 03.12.2015.
 */
public class Activity3 extends Activity {
    private AnimationDrawable mAnimationDrawable;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_3);

        ImageView imageView = (ImageView) findViewById(R.id.imageswitcher);
        imageView.setBackgroundResource(R.drawable.myalpha);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        final Button btnStart = (Button) findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAnimationDrawable.start();
            }
        });

        final Button btnStop = (Button) findViewById(R.id.button2);
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAnimationDrawable.stop();
            }
        });
    }
}