package com.lexa.for4tegroup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    AdapterMy mRecycleAdaper;
    static ArrayList <String> values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    @Override
    protected void onStart() {
        super.onStart();
        if ( !isOnline() ){
            Toast.makeText(getApplicationContext(),
                    "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
            showSettingsAlert(this);

            AdapterMy.mCheckedPosition = -1;
        }



            values = new ArrayList<>();
            values.add("http://www.heartofgreen.org/.a/6a00d83451cedf69e201a73dcaba0a970d-pi");
            values.add("http://images5.fanpop.com/image/photos/27900000/Ocean-Animals-animals-27960311-1920-1200.jpg");

            final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            final LinearLayoutManager linearLayoutManager
                    = new LinearLayoutManager(this);

            mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
            mRecyclerView.setHasFixedSize(true);
            mRecycleAdaper = new AdapterMy(values);
            mRecyclerView.setAdapter(mRecycleAdaper);

    }




    public void checkFoto(View v){
        if(AdapterMy.mCheckedPosition >= 0) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(SecondActivity.KEY_TEXT, AdapterMy.mCheckedPosition);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Ничего не выбранно", Toast.LENGTH_LONG).show();
        }
    }




    public void showSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Не подключен интернет");

        alertDialog.setMessage("Хотите подключить?");

        alertDialog.setPositiveButton("Подключить", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }

}
