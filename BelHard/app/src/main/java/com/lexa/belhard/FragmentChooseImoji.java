package com.lexa.belhard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 25.05.2016.
 */
public class FragmentChooseImoji extends Fragment {

    List<Integer> imojis;
    int buttonNum = 1;

    ImojiAdapter imojiAdapter;
    RecyclerView mRecyclerView;
    GridLayoutManager linearLayoutManager;

    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;

    private OnFragmentInteractionListener3 mListener;

    public interface OnFragmentInteractionListener3 {
        void onFragmentInteraction6 ();
        void onFragmentInteraction5 (int text);

    }


    public static FragmentChooseImoji newInstance() {
        FragmentChooseImoji listFragment2 = new FragmentChooseImoji();
        return listFragment2;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener3) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_imoji, container, false);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        imojis = new ArrayList<>();

        stateImi();

        imageButton = (ImageButton)getActivity().findViewById(R.id.b1);
        imageButton.setSelected(true);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonNum != 1) {
                    changeCha();
                    buttonNum = 1;
                    changeImi(R.array.listArray);
                    imageButton.setSelected(true);
                }
            }
        });

        imageButton2 = (ImageButton)getActivity().findViewById(R.id.b2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( buttonNum != 2) {
                    changeCha();
                    buttonNum = 2;
                    changeImi(R.array.listArray2);
                    imageButton2.setSelected(true);
                }
            }
        });

        imageButton3 = (ImageButton)getActivity().findViewById(R.id.b3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( buttonNum != 3) {
                    changeCha();
                    buttonNum = 3;
                    changeImi(R.array.listArray3);
                    imageButton3.setSelected(true);
                }
            }
        });

        imageButton4 = (ImageButton)getActivity().findViewById(R.id.b4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( buttonNum != 4) {
                    changeCha();
                    buttonNum = 4;
                    changeImi(R.array.listArray4);
                    imageButton4.setSelected(true);
                }
            }
        });

        ImageView imageButton5 = (ImageView)getActivity().findViewById(R.id.btnRight);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction6();
            }
        });

    }


    public void changeImi(int it){
          imojis.clear();
        final int[] imoji = getResources().getIntArray(it);
        for(int i= 0; i < imoji.length; i++){
            imojis.add(imoji[i]);
        }

        imojiAdapter.addItems(imojis);
    }

    private void changeCha(){
        if(buttonNum == 1){
            imageButton.setSelected(false);
        }
        if(buttonNum == 2){
            imageButton2.setSelected(false);
        }
        if(buttonNum == 3){
            imageButton3.setSelected(false);
        }else{
            imageButton4.setSelected(false);
        }
    }

    public void stateImi(){

        int[] imoji = getResources().getIntArray(R.array.listArray);
        for(int i= 0; i < imoji.length; i++){
            imojis.add(imoji[i]);
        }

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView2);
        linearLayoutManager
                = new GridLayoutManager(getActivity(),4);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        imojiAdapter = new ImojiAdapter(imojis);
        mRecyclerView.setAdapter(imojiAdapter);
        imojiAdapter.addItems(imojis);

        imojiAdapter.setItemClickListener(new ImojiAdapter.ItemClickListener() {
            @Override
            public void onClick(int link) {
                mListener.onFragmentInteraction5(link);
            }
        });
    }


    @Override
    public void onPause()
    {
        super.onPause();
    }



    @Override
    public void onResume()
    {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
