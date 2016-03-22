package com.example.lexa.entrytothedoctor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    //Root URL of our web service

    public static final String URL = "https://api.backendless.com/";

    private final String KEY = "225BCBE4-DC36-5375-FF66-FBACE7C6A200";
    private final String KEYID = "02D208FE-DC5F-A3B0-FF35-F317CB730500";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_DOCTOR_ID = "key_doctor_id";
    public static final String KEY_DOCTOR_NAME = "key_doctor_name";
    public static final String KEY_DOCTOR_SURNAME = "key_doctor_surname";
    public static final String KEY_DOCTOR_AGE = "key_doctor_age";
    public static final String KEY_DOCTOR_SPEC = "key_doctor_spec";

    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private DoctorBase doctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listViewBooks);
         Doctors();
    }

    private void Doctors(){
        //While the app fetched data we are displaying a progress dialog
       // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

       Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        Link intf = retrofit.create(Link.class);

        Call<DoctorBase> call = intf.doctor();

        call.enqueue(new Callback<DoctorBase>() {
            @Override
            public void onResponse(Call<DoctorBase> call, Response<DoctorBase> response) {

                if (response.isSuccessful()) {
                    Log.e("dfddgfg", response.body().toString());
                    doctor = response.body();
                    showList();
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                    Log.e("cvbbbbbbbbbbbb", "fdgggggggggggg");
                }

            }

            @Override
            public void onFailure(Call<DoctorBase> call, Throwable t) {

            }
        });
    }


    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[doctor.getData().size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<doctor.getData().size(); i++){
            //Storing names to string array
            items[i] = doctor.getData().get(i).getSpecialization();
       }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
