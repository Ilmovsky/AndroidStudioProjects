package com.lexa.mymaps;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.tileprovider.IRegisterReceiver;


public class MainActivity extends AppCompatActivity implements IRegisterReceiver {

    public static final String KEY_TEXT = "key_text";
    public static long id;
    public static boolean clickOnListPlace = false;
    static FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        id = intent.getLongExtra(KEY_TEXT, 1);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("map").setIndicator(getResources().getString(R.string.Name1)), MapFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("list").setIndicator(getResources().getString(R.string.Name7)), ListFragment.class, null);

        tabHost.setCurrentTabByTag("map");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();
    }

}

