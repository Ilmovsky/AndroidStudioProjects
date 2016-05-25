package com.lexa.belhard;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteractionListener,
                                                               FragmentImoji.OnFragmentInteractionListener2,
                                                               FragmentChooseImoji.OnFragmentInteractionListener3{

    ChatAdapter chatAdapter;
    List<ChatBase> chatBases;

    FragmentImoji fragmentImoji;
    ButtonFragment enterFragment;
    FragmentChooseImoji fragmentChooseImoji;
    EmptyFragment emptyFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatBases = new ArrayList<>();

        addSpeech();

        fragmentManager = getSupportFragmentManager();

        enterFragment = ButtonFragment.newInstance();
        addClose(enterFragment);
        emptyFragment = EmptyFragment.newInstance();
        addClose2(emptyFragment);

    }


    @Override
    public void onFragmentInteraction() {

        fragmentImoji = FragmentImoji.newInstance();
        backClose1(fragmentImoji);
    }

    @Override
    public void onFragmentInteraction2(String text) {
        backClose1(enterFragment);
        if(text.length()!= 0) {
            chatBases.add(new ChatBase(text, "11.25", "R.drawable.monster"));
        chatAdapter.addItems(chatBases);}
    }

    @Override
    public void onFragmentInteraction3() {
            fragmentChooseImoji = FragmentChooseImoji.newInstance();
            backClose2(fragmentChooseImoji);
    }


    @Override
    public void onFragmentInteraction4() {
      backClose2(emptyFragment);
    }


    @Override
    public void onFragmentInteraction5(int text) {
       fragmentImoji.addImoji(text);
    }

    @Override
    public void onFragmentInteraction6() {
        backClose1(enterFragment);
        backClose2(emptyFragment);
        Intent intent2 = new Intent(MainActivity.this, ActivityMarket.class);
        startActivity(intent2);
    }


    @Override
    public void onBackPressed() {
        backClose1(enterFragment);
        backClose2(emptyFragment);
        finish();
    }


    public void backClose1(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void backClose2(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.is_up,
                R.anim.is_down);
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }

    public void addClose(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void addClose2(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container2, fragment);
        fragmentTransaction.commit();
    }

    public void addSpeech(){
        chatBases.add(new ChatBase("Hello", "11.25", "R.drawable.monster"));
        chatBases.add(new ChatBase("Dinosaurus fdgfdgfdgfdgf ddgfdgfdgf", "11.25", "R.drawable.monster"));

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        chatAdapter = new ChatAdapter(this,chatBases);
        mRecyclerView.setAdapter(chatAdapter);
        chatAdapter.addItems(chatBases);
    }

}
