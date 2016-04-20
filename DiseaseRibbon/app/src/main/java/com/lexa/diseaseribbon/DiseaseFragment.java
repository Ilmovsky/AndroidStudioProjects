package com.lexa.diseaseribbon;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lexa on 29.03.2016.
 */
public class DiseaseFragment extends Fragment {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 100;
    int firstVisibleItems, visibleItemCount, totalItemCount;

    int offset;

    public static final String URL = "https://api.backendless.com/";

    final static int PAGE_SIZE = 100;

    AdapterDisease mRecycleAdaper;

    ImageButton imageButton;
    InputMethodManager imm = null;

    static String disease = null;
    static String tableName = null;

    Gson gson = new GsonBuilder()
            .create();

    List<DiseaseList> values;
    DiseaseBase diseaseBase;

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();
    RetrofitInt intf = retrofit.create(RetrofitInt.class);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.disease_fragment, null);

        final RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        final ProgressDialog loadin = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

        final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        values = new ArrayList<DiseaseList>();
        offset = 0;

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterDisease(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        getActivity().setTitle(getResources().getString(R.string.Name4));

        Log.e("values = ", String.valueOf(values));
        loadDisease1(loadin);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItems + visibleThreshold)) {

                    if (diseaseBase.getTotalObjects() > values.size()) {
                        offset = offset + PAGE_SIZE;
                        Log.e("offset = ", String.valueOf(offset));
                        Log.e("values = ", String.valueOf(values));
                        loadDisease();
                    }
                    loading = true;
                }
            }

        });



        final EditText editText = (EditText)v.findViewById(R.id.textTools);

        imageButton = (ImageButton)v.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachDisease(editText,loadin);
            }
        });

        mRecycleAdaper.setItemClickListener(new AdapterDisease.ItemClickListener() {
            @Override
            public void onClick(DiseaseList summa) {
                MainActivity.fragnum = 2;
                MainActivity.fragmentTransaction = getFragmentManager().beginTransaction();
                MainActivity.fragmentTransaction.replace(R.id.container, MainActivity.topicFragment);
                MainActivity.fragmentTransaction.addToBackStack(null);
                MainActivity.fragmentTransaction.commit();
                disease = summa.getDisease();
                tableName = summa.getTableName();
            }
        });

        return v;
    }




    public void seachDisease(EditText editText,ProgressDialog loading){
        if (editText.getText().length() == 0) {
            values.clear();
            loadDisease1(loading);
            imm.hideSoftInputFromWindow(imageButton.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            String diseaseSearc = "disease LIKE '" + editText.getText().toString() + "%'";
            Call<DiseaseBase> call = intf.diseasefind(diseaseSearc);
            imm.hideSoftInputFromWindow(imageButton.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            editText.setText(null);
            call.enqueue(new Callback<DiseaseBase>() {
                @Override
                public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                    if (response.isSuccessful()) {
                        diseaseBase = response.body();
                        values.clear();
                        for (int i = 0; i < diseaseBase.getData().size(); i++) {
                            values.add(diseaseBase.getData().get(i));
                        }

                        mRecycleAdaper.addItems(values);

                    } else {
                        int statusCode = response.code();

                        // handle request errors yourself
                        ResponseBody errorBody = response.errorBody();
                        Log.e("cvbbbbbbbbbbbb", "fdgggggggggggg");
                    }
                }

                @Override
                public void onFailure(Call<DiseaseBase> call, Throwable t) {

                }
            });
        }
    }




    public void loadDisease1(final ProgressDialog loading) {
        Call<DiseaseBase> call = intf.disease1();
        call.enqueue(new Callback<DiseaseBase>() {
            @Override
            public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                if (response.isSuccessful()) {
                    diseaseBase = response.body();
                    for (int i = 0; i < diseaseBase.getData().size(); i++) {
                        values.add(diseaseBase.getData().get(i));

                    }
                    Log.e("values1 = ", String.valueOf(values.size()));
                    mRecycleAdaper.addItems(values);

                    loading.dismiss();
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<DiseaseBase> call, Throwable t) {
            }
        });
    }





    public void loadDisease(){
        Call<DiseaseBase> call = intf.disease(offset);
        call.enqueue(new Callback<DiseaseBase>() {
            @Override
            public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                if (response.isSuccessful()) {
                    diseaseBase = response.body();
                    for (int i = 0; i < diseaseBase.getData().size(); i++) {
                            values.add(diseaseBase.getData().get(i));

                    }
                    Log.e("values1 = ", String.valueOf(values.size()));
                    mRecycleAdaper.addItems(values);
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                }
            }
            @Override
            public void onFailure(Call<DiseaseBase> call, Throwable t) {}
        });
    }

}