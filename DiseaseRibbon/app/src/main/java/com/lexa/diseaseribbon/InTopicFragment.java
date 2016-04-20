package com.lexa.diseaseribbon;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
 * Created by Lexa on 06.04.2016.
 */
public class InTopicFragment extends Fragment {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 100;
    int firstVisibleItems, visibleItemCount, totalItemCount;

    int offset;
    ProgressDialog loadin;

    public static final String URL = "https://api.backendless.com/";
    public static String userName = "AlexIlm";
    public static String photoUrl = "https://api.backendless.com/02D208FE-DC5F-A3B0-FF35-F317CB730500/v1/files/2051393.jpg";

    AdapterInTopic mRecycleAdaper;

    InTopicBase inTopicBase;

    String findTopic;

    ImageButton imageView;

    String thisTableName;

    Gson gson = new GsonBuilder()
            .create();

    List<InTopicList> values;

    InputMethodManager imm = null;


    InTopicList inTopicList1;

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();
    RetrofitInt intf = retrofit.create(RetrofitInt.class);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.in_topic_fragment, null);

        final RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);


       imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        loadin = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

        values = new ArrayList<InTopicList>();
        offset = 0;

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterInTopic(getActivity(),values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        thisTableName = DiseaseFragment.tableName;
        findTopic ="topic=" + "'" + TopicFragment.themeName + "'";
        getActivity().setTitle(TopicFragment.themeName);

        loadDiseaseTopic1(loadin);

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

                    if (inTopicBase.getTotalObjects() > values.size()) {
                        offset = offset + DiseaseFragment.PAGE_SIZE;
                        Log.e("offset = ", String.valueOf(offset));
                        Log.e("values = ", String.valueOf(values));
                        loadDiseaseTopic();
                    }
                    loading = true;
                }
            }

        });

        final EditText editText = (EditText)v.findViewById(R.id.textTools);

        imageView = (ImageButton)v.findViewById(R.id.imageButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTopicText(editText);
            }
        });

        return v;
    }

    public void loadDiseaseTopic1(final ProgressDialog loading) {
        Call<InTopicBase> call = intf.topic(thisTableName,findTopic);
        call.enqueue(new Callback<InTopicBase>() {
            @Override
            public void onResponse(Call<InTopicBase> call, Response<InTopicBase> response) {
                if (response.isSuccessful()) {
                    inTopicBase = response.body();
                    for (int i = 0; i < inTopicBase.getData().size(); i++) {
                        values.add(inTopicBase.getData().get(i));
                    }
                    mRecycleAdaper.addItems(values);

                    loading.dismiss();

                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                    Log.e("cvbbbbbbbbbbbb", "fdgggggggggggg");
                }
            }

            @Override
            public void onFailure(Call<InTopicBase> call, Throwable t) {

            }
        });
    }




    public void loadDiseaseTopic() {
        Call<InTopicBase> call = intf.topic1(thisTableName, findTopic,offset);
        call.enqueue(new Callback<InTopicBase>() {
            @Override
            public void onResponse(Call<InTopicBase> call, Response<InTopicBase> response) {
                if (response.isSuccessful()) {
                    inTopicBase = response.body();
                    for (int i = 0; i < inTopicBase.getData().size(); i++) {
                        values.add(inTopicBase.getData().get(i));
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
            public void onFailure(Call<InTopicBase> call, Throwable t) {

            }
        });
    }



    public void loadDiseaseTopic2() {
        Call<InTopicBase> call = intf.topic(thisTableName, findTopic);
        call.enqueue(new Callback<InTopicBase>() {
            @Override
            public void onResponse(Call<InTopicBase> call, Response<InTopicBase> response) {
                if (response.isSuccessful()) {
                    inTopicBase = response.body();
                    values.clear();
                    for (int i = 0; i < inTopicBase.getData().size(); i++) {
                        values.add(inTopicBase.getData().get(i));
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
            public void onFailure(Call<InTopicBase> call, Throwable t) {

            }
        });
    }








            public void addTopicText(final EditText editText){

                if (editText.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.Toast2, Toast.LENGTH_LONG).show();
                    imm.hideSoftInputFromWindow(imageView.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } else {

                    InTopicList inTopicList = new InTopicList(TopicFragment.themeName, editText.getText().toString(),String.valueOf(inTopicBase.getTotalObjects() + 1), userName, photoUrl);
                    Call<InTopicList> call = intf.topicPut2(thisTableName, inTopicList);
                    imm.hideSoftInputFromWindow(imageView.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    editText.setText(null);
                    call.enqueue(new Callback<InTopicList>() {
                        @Override
                        public void onResponse(Call<InTopicList> call, Response<InTopicList> response) {

                            if (response.isSuccessful()) {
                                inTopicList1 = response.body();
                                loadDiseaseTopic2();
                            } else {
                                int statusCode = response.code();

                                // handle request errors yourself
                                ResponseBody errorBody = response.errorBody();
                                Log.e("cvbbbbbbbbbbbb", String.valueOf(21));
                            }

                        }

                        @Override
                        public void onFailure(Call<InTopicList> call, Throwable t) {

                        }
                    });
                }
            }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.in_topic_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                loadDiseaseTopic2();
                break;
        }
        return true;
    }

}
