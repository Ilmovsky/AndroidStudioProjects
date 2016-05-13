package com.lexa.newnewstytby;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lexa.newnewstytby.fragments.EnterFragment;
import com.lexa.newnewstytby.fragments.ListFragment;
import com.lexa.newnewstytby.fragments.NewsFragment;
import com.lexa.newnewstytby.ormLite.BaseClass;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.util.List;


public class MainActivity extends AppCompatActivity implements EnterFragment.OnFragmentInteractionListener,
                                                               ListFragment.OnFragmentInteractionListener2,
                                                               NewsFragment.OnFragmentInteractionListener3{


    private FragmentManager fragmentManager;
    private FragmentTransaction  fragmentTransaction;

    static List<BaseClass>needObjectNews;
    static Bundle savedInstanceState;
    ListFragment listFragment;
    static int fragnum = 1;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();



        fragmentManager = getSupportFragmentManager();

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("SAVE_FRAGMENT");

        if(savedInstanceState == null) {
            getEnterFragment();
        }

        checkForUpdates();
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    protected void onPause() {

        if (listFragment != null) {

            needObjectNews = listFragment.getModel();
            savedInstanceState = listFragment.getInsState();
        }
        super.onPause();
        unregisterManagers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterManagers();
    }

    public void getEnterFragment() {
        EnterFragment enterFragment = EnterFragment.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, enterFragment);
        fragmentTransaction.commit();
    }





    @Override
    public void onFragmentInteraction(List<BaseClass> needObjectNews, boolean isNewNews) {

        listFragment = ListFragment.newInstance(isNewNews);
        listFragment.getNeedObjectNewObj(needObjectNews);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, listFragment, "SAVE_FRAGMENT");
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteractionEnd() {

        finish();

    }

    @Override
    public void onFragmentInteractionEnd3() {

        fragnum =1;
        frag();

    }


    @Override
    public void onFragmentInteraction2(String link) {

       listFragment.knowInstanceState();

        if (listFragment != null) {
            needObjectNews = listFragment.getModel();
            savedInstanceState = listFragment.getInsState();
        }

        fragnum =2;
        NewsFragment newsFragment = NewsFragment.newInstance(link);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, newsFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {
        switch (fragnum){
            case 2:
                fragnum =1;
                frag();
                break;
            case 1: finish();
                break;
        }
    }

    public void frag(){
        listFragment = ListFragment.newInstance(false);
        listFragment.getNeedObjectNewObj(needObjectNews);
        listFragment.setInsState(savedInstanceState);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, listFragment, "SAVE_FRAGMENT");
        fragmentTransaction.commit();
    }


    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }


    private void checkForCrashes() {

        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }




}


