package com.lexa.for4tegroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Lexa on 05.04.2016.
 */
public class SecondActivity extends AppCompatActivity {

    public static final String KEY_TEXT = "key_text";
    int urlNumber;
    String urlThis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Intent intent = getIntent();
        urlNumber = intent.getIntExtra(KEY_TEXT,1);
         urlThis = MainActivity.values.get(urlNumber);

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        ImageManager IM = new ImageManager();
        IM.fetchImage(urlThis, imageView);
    }

}
