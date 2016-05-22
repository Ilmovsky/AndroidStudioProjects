package com.lexa.tripworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lexa.tripworld.delay_auto_complete.DelayAutoCompleteTextView;
import com.lexa.tripworld.delay_auto_complete.MyAdapter;
import com.lexa.tripworld.internet_broadcast.BroadcastConnection;
import com.lexa.tripworld.location_listener.LocListener;
import com.lexa.tripworld.location_listener.MyLocationThred;
import com.lexa.tripworld.parseObject.ListOfCities;

import java.util.Calendar;

public class MainActivity extends Activity implements BroadcastConnection.OnNetReceivedListener {

    boolean isNet;
    BroadcastConnection broadcastConnection;
    private TextView dateView;
    private int year, month, day;
    DelayAutoCompleteTextView addCity;
    DelayAutoCompleteTextView addCity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView) findViewById(R.id.date_trip);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        addCity = (DelayAutoCompleteTextView) findViewById(R.id.city_title);
        inicializeText(addCity, 1);
        addCity2 = (DelayAutoCompleteTextView) findViewById(R.id.city_title2);
        inicializeText(addCity2, 2);

        broadcastConnection = new BroadcastConnection();
        broadcastConnection.setOnNetReceivedListener(this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastConnection, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyLocationThred myLocationThred = new MyLocationThred(this);
        myLocationThred.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
       LocListener.closeManager();
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastConnection);
    }

    public void onClickFind(View view) {

        if(addCity.getText().length()==0||addCity.getText().length()==0||dateView.getText().toString().equals("Change Date")){
            Toast.makeText(this, getResources().getString(R.string.Toast), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, getResources().getString(R.string.Toast2), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNetReceived(boolean isNet) {
        this.isNet = isNet;
    }

    public void inicializeText(final DelayAutoCompleteTextView addCity, int num){
        addCity.setThreshold(1);
        addCity.setAdapter(new MyAdapter(this));
        if(num == 1){
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFcc0000,
                android.graphics.PorterDuff.Mode.MULTIPLY);
            addCity.setLoadingIndicator(progressBar);
        }
        else  {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar2);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFcc0000,
                    android.graphics.PorterDuff.Mode.MULTIPLY);
            addCity.setLoadingIndicator(progressBar);
        }
        addCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListOfCities cities = (ListOfCities) adapterView.getItemAtPosition(position);
                addCity.setText(cities.getName());
            }
        });
    }



    @SuppressWarnings("deprecation")
    public void onClickDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2+1, arg3);
        }
    };


    private void showDate(int year, int month, int day) {
        String day1;
        String month1;
        if(day < 10)
            day1 = "0"+day;
        else day1 = String.valueOf(day);
        if(month < 10)
            month1 = "0"+month;
        else month1 = String.valueOf(month);
        dateView.setText(new StringBuilder().append(day1).append(".")
                .append(month1).append(".").append(year));
    }

}
