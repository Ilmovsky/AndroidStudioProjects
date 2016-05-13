package com.lexa.newnewstytby.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lexa.newnewstytby.R;
import com.lexa.newnewstytby.retrofit.RetrofitInic;
import com.lexa.newnewstytby.retrofit.RetrofitInt;
import com.lexa.newnewstytby.internet_connection.Checinternet;
import com.lexa.newnewstytby.object.RssInfo;
import com.lexa.newnewstytby.ormLite.BaseClass;
import com.lexa.newnewstytby.ormLite.HelperFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lexa on 05.05.2016.
 */
public class EnterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Dialog dlg =null;


    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(List<BaseClass> needObjectNews,boolean isNewNews);

        public void onFragmentInteractionEnd();
    }


    public static EnterFragment newInstance() {
        EnterFragment enterFragment = new EnterFragment();
        return enterFragment;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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
        View view = inflater.inflate(R.layout.enter_fragment, container, false);
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();

        loadNewData();

    }


    public void loadNewData(){

        final ProgressDialog loadin = ProgressDialog.show(getActivity(), getResources().getString(R.string.Progress),
                getResources().getString(R.string.Progress2), false, false);

        if (Checinternet.checkInternet(getActivity())) {

                    retrofitLoad(loadin);

        } else{

            HelperFactory.setHelper(getContext());
            List<BaseClass> rssInfoList = new ArrayList<BaseClass>();
            try {
                rssInfoList = HelperFactory.getHelper().getBaseClassDAO().getAllBaseClasses();
            } catch (SQLException e) {

                e.printStackTrace();
            }

            if(rssInfoList.size() == 0) {
               internetDialog(loadin);
            }
            else {
                loadin.dismiss();
                boolean isNewNews = false;
                mListener.onFragmentInteraction(rssInfoList, isNewNews);
                HelperFactory.releaseHelper();
            }
        }
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
                loadNewData();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                HelperFactory.releaseHelper();
                mListener.onFragmentInteractionEnd();
            }
        });
        dlg.show();
        ((TextView) dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        loadin.dismiss();
    }


    public void retrofitLoad(final ProgressDialog loadin){

        RetrofitInt intf = RetrofitInic.retroinc();
        Call<RssInfo> call = intf.readListNews();

        call.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {

                    RssInfo dsass = response.body();
                    List<BaseClass> rssInfoList = new ArrayList<BaseClass>();

                    for (int i = 0; i < dsass.getChanel().getItem().size(); i++) {

                        BaseClass needObjectNew = new BaseClass(dsass.getChanel().getItem().get(i).getTitle()
                                , dsass.getChanel().getItem().get(i).getLink()
                                , dsass.getChanel().getItem().get(i).getPubDate()
                                , dsass.getChanel().getItem().get(i).getMedia().get(0).getUrl());

                        rssInfoList.add(needObjectNew);
                    }

                    loadin.dismiss();
                    boolean isNewNews = true;
                    mListener.onFragmentInteraction(rssInfoList, isNewNews );

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


}
