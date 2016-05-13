package com.lexa.newnewstytby.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lexa.newnewstytby.R;
import com.lexa.newnewstytby.retrofit.RetrofitInt;
import com.lexa.newnewstytby.adapter.AdapterNews;
import com.lexa.newnewstytby.internet_connection.Checinternet;
import com.lexa.newnewstytby.object.RssInfo;
import com.lexa.newnewstytby.ormLite.BaseClass;
import com.lexa.newnewstytby.retrofit.RetrofitInic;
import com.lexa.newnewstytby.servise.IntentLoadService;


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lexa on 05.05.2016.
 */

public class ListFragment extends Fragment {

    Bundle savedInstanceState;

    List<BaseClass>needObjectNews = new ArrayList<>();
    RecyclerView mRecyclerView;

    boolean isNewNews;

    Dialog dlg =null;
    AdapterNews mRecycleAdaper;

    private OnFragmentInteractionListener2 mListener;

    public interface OnFragmentInteractionListener2 {

         void onFragmentInteraction2 (String link);
    }


    public static ListFragment newInstance(boolean someBoolean) {
        ListFragment listFragment = new ListFragment();
        Bundle args = new Bundle();
        args.putBoolean("someBoolean", someBoolean);
        listFragment.setArguments(args);
        return listFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isNewNews = getArguments().getBoolean("someBoolean");

    }


    public List<BaseClass> getModel(){
        return  needObjectNews;
    }

    public Bundle getInsState(){
        return  savedInstanceState;
    }

    public void setInsState(Bundle savedInstanceState){
        this.savedInstanceState = savedInstanceState;

    }

    public void getNeedObjectNewObj(List<BaseClass> needObjectNews){
        this.needObjectNews = needObjectNews;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        loadfromInternet();
            addToBase();

        ImageButton imageButton = (ImageButton)getActivity().findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMyList();
            }
        });

    }


    @Override
    public void onPause()
    {
        knowInstanceState();
        super.onPause();
    }


    public void knowInstanceState(){
        Bundle outState = new Bundle();
        outState.putParcelable("classname.recycler.layout", mRecyclerView.getLayoutManager().onSaveInstanceState());
        savedInstanceState = outState;
    }



    @Override
    public void onResume()
    {
        super.onResume();

        if(savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("classname.recycler.layout");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void addToBase(){

        if (isNewNews) {
            isNewNews = false;

            Intent intentMyIntentService = new Intent(getActivity(), IntentLoadService.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("needObjectNews", (ArrayList<? extends Parcelable>) needObjectNews);
            intentMyIntentService.putExtras(bundle);
            getActivity().startService(intentMyIntentService);
        }

    }


    public void updateMyList(){
        final ProgressDialog loadin = ProgressDialog.show(getActivity(), getResources().getString(R.string.Progress),
                getResources().getString(R.string.Progress2), false, false);

        if (Checinternet.checkInternet(getActivity())) {

            retrofitLoad(loadin);

        } else{
            internetDialog(loadin);
        }
    }



    public void loadfromInternet(){
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterNews(getActivity(),needObjectNews);
        mRecyclerView.setAdapter(mRecycleAdaper);
        mRecycleAdaper.addItems(needObjectNews);

        mRecycleAdaper.setItemClickListener(new AdapterNews.ItemClickListener() {
            @Override
            public void onClick(String link) {

                mListener.onFragmentInteraction2(link);

            }
        });
    }



    public void retrofitLoad(final ProgressDialog loadin){

        RetrofitInt intf = RetrofitInic.retroinc();
        Call<RssInfo> call = intf.readListNews();

        call.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {

                    RssInfo dsass = response.body();
                    if (dsass.getChanel().getItem().get(0).getTitle().equalsIgnoreCase(needObjectNews.get(0).getName())) {
                        Toast.makeText(getContext(), getResources().getString(R.string.Toast1), Toast.LENGTH_SHORT).show();
                    } else {
                        List<BaseClass> rssInfoList = new ArrayList<BaseClass>();
                        for (int i = 0; i < dsass.getChanel().getItem().size(); i++) {
                            BaseClass needObjectNew = new BaseClass(dsass.getChanel().getItem().get(i).getTitle()
                                    , dsass.getChanel().getItem().get(i).getLink()
                                    , dsass.getChanel().getItem().get(i).getPubDate()
                                    , dsass.getChanel().getItem().get(i).getMedia().get(0).getUrl());
                            rssInfoList.add(needObjectNew);
                        }
                        getNeedObjectNewObj(rssInfoList);
                        mRecycleAdaper.addItems(needObjectNews);
                        isNewNews = true;
                        Toast.makeText(getContext(), getResources().getString(R.string.Toast3), Toast.LENGTH_SHORT).show();
                    }

                    loadin.dismiss();
                        addToBase();

                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<RssInfo> call, Throwable t) {
                Log.e("dgfdgf", String.valueOf(t));
            }
        });
    }


    public void internetDialog(ProgressDialog loadin){
        dlg = new Dialog(this.getActivity());
        dlg.setContentView(R.layout.dialog_internet);
        dlg.setTitle(getResources().getString(R.string.DialogName));
        dlg.setCancelable(false);

        Button btn1 = (Button) dlg.findViewById(R.id.button);
        Button btn2 = (Button) dlg.findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                updateMyList();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        dlg.show();
        ((TextView) dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        loadin.dismiss();
    }

}

