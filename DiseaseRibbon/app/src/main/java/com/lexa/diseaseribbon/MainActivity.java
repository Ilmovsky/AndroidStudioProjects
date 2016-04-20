package com.lexa.diseaseribbon;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static int fragnum = 1;

   static android.app.FragmentTransaction fragmentTransaction;

    static android.app.Fragment diseaseFragment = new DiseaseFragment();
    static android.app.Fragment topicFragment = new TopicFragment();
    static android.app.Fragment inTopicFragment = new InTopicFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, diseaseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        switch (fragnum){
            case 3:  fragnum = 2;
                frag2();
                break;
            case 2:  fragnum = 1;
                frag1();
                break;
            case 1: finish();
                break;
        }
    }


    public void frag1(){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, diseaseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void frag2(){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, topicFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
