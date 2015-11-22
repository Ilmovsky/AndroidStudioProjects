package com.example.igor.les4;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private List<String> list = new ArrayList<>();
    private ListView mListView;
    private EditText mEditText;
    private static final String KEY_EDIT_TEXT = "KEY_EDIT_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mEditText = (EditText) findViewById(R.id.editText); //1

        list.add("element 1");
        list.add("element 2");
        list.add("element 3");
        list.add("element 4");
        list.add("element 5");
        list.add("element 6");
        list.add("element 7");
        list.add("element 8");
        list.add("element 9");

        mListView = (ListView) findViewById(R.id.listView);

        MyAdapter myAdapter = new MyAdapter(this, list);
        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(MainActivity.class.getSimpleName(), "click = " + position);
                Toast.makeText(MainActivity.this, position + " selected", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause(); //Android Asset Studio; TinyPNG

        /**SharedPreferences sharedPreferences = //1
                PreferenceManager.getDefaultSharedPreferences(this);//1

        if(!TextUtils.isEmpty(mEditText.getText())){//1
            sharedPreferences.edit().putString(KEY_EDIT_TEXT,//1
                    String.valueOf(mEditText.getText())).apply();//1
        } else {//1
            sharedPreferences.edit().remove(KEY_EDIT_TEXT);//1
        }//1*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**SharedPreferences sharedPreferences =//1
                PreferenceManager.getDefaultSharedPreferences(this);//1
        String text = sharedPreferences.getString(KEY_EDIT_TEXT, "");//1
        mEditText.setText(text);//1*/
    }
}
