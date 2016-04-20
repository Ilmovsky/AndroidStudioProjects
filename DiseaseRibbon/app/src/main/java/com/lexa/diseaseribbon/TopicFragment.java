package com.lexa.diseaseribbon;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

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
public class TopicFragment extends Fragment {

    Dialog dlg1 = null;
    Dialog dlg2 = null;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 100;
    int firstVisibleItems, visibleItemCount, totalItemCount;

    int offset;

    private int totalObjects = 0;

    public static String themeName;
    public static final String URL = "https://api.backendless.com/";

    AdapterDisease mRecycleAdaper;

    DiseaseBase diseaseBase;
    DiseaseBase diseaseBase1;

    Gson gson = new GsonBuilder()
            .create();

    List<DiseaseList> values;

    DiseaseList deDiseaseList;
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





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.topic_fragment, null);

        final RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        final ProgressDialog loadin = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

        values = new ArrayList<DiseaseList>();
        offset = 0;

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterDisease(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        final String disease ="disease=" + "'" + DiseaseFragment.disease + "'";
        getActivity().setTitle(DiseaseFragment.disease);
        loadDiseaseTopic2();
        loadDiseaseTopic1(loadin, disease);

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
                        offset = offset + DiseaseFragment.PAGE_SIZE;
                        Log.e("offset = ", String.valueOf(offset));
                        Log.e("values = ", String.valueOf(values));
                        loadDiseaseTopic(disease);
                    }
                    loading = true;
                }
            }

        });


        mRecycleAdaper.setItemClickListener(new AdapterDisease.ItemClickListener() {
            @Override
            public void onClick(DiseaseList summa) {
                themeName = summa.getTopic();
                openTopic();
            }
        });

        return v;
    }




    public void openTopic(){
        MainActivity.fragnum = 3;
        MainActivity.fragmentTransaction = getFragmentManager().beginTransaction();
        MainActivity.fragmentTransaction.replace(R.id.container, MainActivity.inTopicFragment);
        MainActivity.fragmentTransaction.addToBackStack(null);
        MainActivity.fragmentTransaction.commit();
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.topic_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
               addTopic();
                break;

            case R.id.action_find:

                break;
        }
        return true;
    }




    public void addTopic() {
        dlg1 = new Dialog(this.getActivity());
        dlg1.setContentView(R.layout.dialog_add_topic);
        dlg1.setTitle(getResources().getString(R.string.Name));
        dlg1.setCancelable(true);

        final Button btn3 = (Button)dlg1.findViewById(R.id.button);
        Button btn4 = (Button)dlg1.findViewById(R.id.button2);

        final EditText editText1 = (EditText)dlg1.findViewById(R.id.input_text);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDiseasseTopic(editText1);
                addDiseasseInTopic(editText1);
                openTopic();
                    dlg1.dismiss();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg1.dismiss();
            }
        });
        dlg1.show();
        ((TextView) dlg1.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
    }






    public void addDiseasseTopic(EditText editText){
        if (editText.getText().length() == 0) {
            Toast.makeText(getActivity(), R.string.Toast, Toast.LENGTH_LONG).show();

        } else {
            DiseaseList diseaseList = new DiseaseList(DiseaseFragment.tableName, editText.getText().toString(),DiseaseFragment.disease, String.valueOf(totalObjects + 1) );
            Call<DiseaseList> call = intf.topicPut(diseaseList);
            call.enqueue(new Callback<DiseaseList>() {
                @Override
                public void onResponse(Call<DiseaseList> call, Response<DiseaseList> response) {

                    if (response.isSuccessful()) {
                        deDiseaseList = response.body();
                        loadDiseaseTopic2();

                    } else {
                        int statusCode = response.code();

                        // handle request errors yourself
                        ResponseBody errorBody = response.errorBody();
                        Log.e("cvbbbbbbbbbbbb", "fdgggggggggggg");
                    }

                }

                @Override
                public void onFailure(Call<DiseaseList> call, Throwable t) {

                }
            });
        }
    }






    public void addDiseasseInTopic(EditText editText){
        if (editText.getText().length() == 0) {
            Toast.makeText(getActivity(), R.string.Toast, Toast.LENGTH_LONG).show();

        } else {

            InTopicList inTopicList = new InTopicList(editText.getText().toString(), "namethis" + getResources().getString(R.string.Name2),"1","namethis","https://api.backendless.com/02D208FE-DC5F-A3B0-FF35-F317CB730500/v1/files/media/bukva_a.jpg");
            themeName = editText.getText().toString();
            Call<InTopicList> call = intf.topicPut2(DiseaseFragment.tableName, inTopicList);
            call.enqueue(new Callback<InTopicList>() {
                @Override
                public void onResponse(Call<InTopicList> call, Response<InTopicList> response) {

                    if (response.isSuccessful()) {
                        inTopicList1 = response.body();
                    } else {
                        int statusCode = response.code();

                        // handle request errors yourself
                        ResponseBody errorBody = response.errorBody();
                        Log.e("cvbbbbbbbbbbbb", "fdgggggggggggg");
                    }

                }

                @Override
                public void onFailure(Call<InTopicList> call, Throwable t) {

                }
            });
        }
    }










    public void loadDiseaseTopic1(final ProgressDialog loading, String disease) {
        Call<DiseaseBase> call = intf.topics(disease);
        call.enqueue(new Callback<DiseaseBase>() {
            @Override
            public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                if (response.isSuccessful()) {
                    diseaseBase = response.body();
                    for (int i = 0; i < diseaseBase.getData().size(); i++) {
                        values.add(diseaseBase.getData().get(i));
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
            public void onFailure(Call<DiseaseBase> call, Throwable t) {

            }
        });
    }




    public void loadDiseaseTopic(String disease) {
        Call<DiseaseBase> call = intf.topics1(disease, offset);
        call.enqueue(new Callback<DiseaseBase>() {
            @Override
            public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                if (response.isSuccessful()) {
                    diseaseBase = response.body();
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


    public void loadDiseaseTopic2() {
        Call<DiseaseBase> call = intf.topics2();
        call.enqueue(new Callback<DiseaseBase>() {
            @Override
            public void onResponse(Call<DiseaseBase> call, Response<DiseaseBase> response) {
                if (response.isSuccessful()) {
                    diseaseBase1 = response.body();
                    totalObjects = diseaseBase1.getTotalObjects();

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
