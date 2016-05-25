package com.lexa.belhard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Lexa on 25.05.2016.
 */
public class ActivityMarket extends AppCompatActivity{

    static FragmentTabHost tabHost;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        fragmentManager = getSupportFragmentManager();

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        try {
            tabHost.setup(this, fragmentManager, android.R.id.tabcontent);
        }catch (Exception e){
            Log.e("dgdf", String.valueOf(e));
        }

        tabHost.addTab(tabHost.newTabSpec("marc").setIndicator(getResources().getString(R.string.tab1), null), MarketFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("marc2").setIndicator(getResources().getString(R.string.tab2), null), Marketfragment2.class, null);

        tabHost.setCurrentTabByTag("marc");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabHost = null;
    }
}
