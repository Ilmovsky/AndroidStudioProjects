package com.example.lexa.alcho;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import com.example.lexa.alcho.construct.AlcoBase;
import com.example.lexa.alcho.fragm_inform.Fragment1;
import com.example.lexa.alcho.fragm_inform.Fragment2;

import java.util.List;

/**
 * Created by Lexa on 21.12.2015.
 */
public class Inform extends FragmentActivity   {

    public static final String KEY_TEXT = "key_text";
    List<AlcoBase> values = null;

    static public String text = null;
    static public String text1 = null;
    static public String text2 = null;
    long i = 0;
    int j = 0;
    private android.app.Fragment fragment1 = new Fragment1();
    private android.app.Fragment fragment2 = new Fragment2();
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform);

        ListAlcho.datasource.open();
        values = ListAlcho.datasource.getAllAlcoBase();

        Intent intent = getIntent();
        i = intent.getLongExtra(KEY_TEXT,1);
        for(j = 0; j < values.size(); j++){
          if (values.get(j).getId() == i){

              text2 = values.get(j).getImage();

              text =  values.get(j).getName();

              text1 = values.get(j).getSite();
              break;
          }
        }

        transaction = getFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.animator.left, R.animator.right);

        transaction.replace(R.id.fragment, fragment1);

        transaction.commit();



        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction();


                transaction.setCustomAnimations(R.animator.left, R.animator.right);
                j = j + 1;
                if (fragment1.isVisible()) {
                    transaction.replace(R.id.fragment, fragment2);
                    if (j < values.size()) {
                        text = values.get(j).getName();
                        text2 = values.get(j).getImage();
                        text1 = values.get(j).getSite();

                    } else {
                        j = 0;
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();
                    }

                } else {
                    transaction.replace(R.id.fragment, fragment1);
                    if (j < values.size()) {
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();

                    } else {
                        j = 0;
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();
                    }
                }

                transaction.commit();
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.animator.right2, R.animator.left2);

                j=j-1;
                if (fragment1.isVisible()) {
                    transaction.replace(R.id.fragment, fragment2);
                    if (j >= 0) {
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();

                    } else {
                        j = values.size()-1;
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();
                    }

                } else {
                    transaction.replace(R.id.fragment, fragment1);
                    if (j >=0) {
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();
                    } else {
                        j = values.size()-1;
                        text = values.get(j).getName();
                        text1 = values.get(j).getSite();
                        text2 = values.get(j).getImage();
                    }
                }
                transaction.commit();
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inform.this, VvodAlcho.class);
                intent.putExtra(VvodAlcho.KEY_TEXT1,j);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        ListAlcho.datasource.close();
        super.onPause();
    }

}