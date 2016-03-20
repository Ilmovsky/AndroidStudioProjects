package com.lexa.mymaps;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lexa on 02.02.2016.
 */
public class FirstActivity extends Activity {
    public static DataSource datasource;
    MapsBase mapsBase = null;

    AlertDialog.Builder builder;

    Dialog dlg = null;
    List<String> maps = null;
    List<String> maps1 = null;
    List<MapsBase> values = null;
    private AdapterMaps mRecycleAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        maps = new LinkedList<>();
        maps1 = new ArrayList<>();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        datasource = new DataSource(this);
        datasource.open();

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

        values = datasource.getAllMapsBase();

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterMaps(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        String path = Environment.getExternalStorageDirectory().getPath();
        if(path !=null) {
            File f = new File(path);
            File file[] = f.listFiles();
            for (File aFile : file) {
                if (aFile.getName().contains(".map"))
                    maps.add(aFile.getName());
            }


            for (int i = 0; i < values.size(); i++) {
                boolean exist = false;
                for (int j = 0; j < maps.size(); j++) {
                    if (values.get(i).getCountry().equalsIgnoreCase(maps.get(j)))
                        exist = true;
                }
                if (!exist) {
                    datasource.deleteMapsBase(values.get(i));
                }
            }


            for (int i = 0; i < maps.size(); i++) {
                boolean exist = false;
                for (int j = 0; j < values.size(); j++) {
                    if (values.get(j).getCountry().equalsIgnoreCase(maps.get(i)))
                        exist = true;
                }
                if (exist) {
                    maps1.add(maps.get(i));
                }
            }

           maps.removeAll(maps1);

            values = datasource.getAllMapsBase();
            mRecycleAdaper.addItems(values);


          if(maps.size() > 0) {
              onCreateListDialog(maps);
          }


            mRecycleAdaper.setItemClickListener(new AdapterMaps.ItemClickListener() {
                @Override
                public void onClick(MapsBase summa) {
                    Intent intent2 = new Intent(FirstActivity.this, MainActivity.class);
                    intent2.putExtra(MainActivity.KEY_TEXT, summa.getId());
                    startActivity(intent2);
                }
            });
        }
        else
            Toast.makeText(FirstActivity.this, R.string.Toast4, Toast.LENGTH_LONG).show();
    }






    public Dialog onCreateListDialog(final List<String>maps){
        final String [] map = new String [maps.size()];
        for (int i =0; i < maps.size(); i++){
            map [i] = maps.get(i);
        }

        builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.Name2))
                .setNegativeButton(getResources().getString(R.string.Button2),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (values.size() == 0) {
                                    Toast.makeText(FirstActivity.this, getResources().getString(R.string.Toast7), Toast.LENGTH_SHORT).show();
                                    builder.show();
                                }
                            }
                        });
        builder.setItems(map, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                addMapDialog(map[item], maps, item);
            }
        });
        builder.setCancelable(false);
        return  builder.show();
    }







    public void addMapDialog(final String map,final List<String>maps, final int item) {
        dlg = new Dialog(FirstActivity.this);
        dlg.setContentView(R.layout.dialog_add_maps);
        String titles = getResources().getString(R.string.Name1);
        String titles2 = titles + " " + map;
        dlg.setTitle(titles2);
        dlg.setCancelable(false);

        final EditText userInput = (EditText) dlg.findViewById(R.id.input_text);
        final EditText userInput2 = (EditText) dlg.findViewById(R.id.input_text2);
        final EditText userInput3 = (EditText) dlg.findViewById(R.id.input_text3);
        final Button btn2 = (Button) dlg.findViewById(R.id.button2);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(values.size() == 0){
                    Toast.makeText(FirstActivity.this,getResources().getString(R.string.Toast7),Toast.LENGTH_SHORT).show();
                    imm.hideSoftInputFromWindow(btn2.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    onCreateListDialog(maps);
                    FirstActivity.this.dlg.dismiss();
                }
                else{
                    FirstActivity.this.dlg.dismiss();
                }

            }
        });
        final Button btn = (Button) dlg.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       if (userInput.getText().length() == 0) {
                                           Toast.makeText(FirstActivity.this, R.string.Toast, Toast.LENGTH_LONG).show();
                                           imm.hideSoftInputFromWindow(btn.getWindowToken(),
                                                   InputMethodManager.HIDE_NOT_ALWAYS);
                                       } else if ((userInput2.getText().length() == 0) || (userInput3.getText().length() == 0)) {
                                           Toast.makeText(FirstActivity.this, R.string.Toast2, Toast.LENGTH_LONG).show();
                                           imm.hideSoftInputFromWindow(btn.getWindowToken(),
                                                   InputMethodManager.HIDE_NOT_ALWAYS);
                                       } else if (!parser(userInput2.getText().toString(), userInput3.getText().toString())) {
                                           Toast.makeText(FirstActivity.this, R.string.Toast3, Toast.LENGTH_LONG).show();
                                           imm.hideSoftInputFromWindow(btn.getWindowToken(),
                                                   InputMethodManager.HIDE_NOT_ALWAYS);
                                       } else {
                                           mapsBase = datasource.createMapsBase(map, userInput.getText().toString().toUpperCase(), Double.parseDouble(userInput2.getText().toString()), Double.parseDouble(userInput3.getText().toString()), 16);
                                           values = datasource.getAllMapsBase();
                                           mRecycleAdaper.addItems(values);
                                           maps.remove(item);
                                           imm.hideSoftInputFromWindow(btn.getWindowToken(),
                                                   InputMethodManager.HIDE_NOT_ALWAYS);
                                           if (maps.size() > 0) {
                                               onCreateListDialog(maps);
                                               FirstActivity.this.dlg.dismiss();
                                           } else {
                                               FirstActivity.this.dlg.dismiss();
                                           }

                                       }
                                   }
                               }

        );
        dlg.show();
        ((TextView) dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
    }







    public boolean parser(String text1, String text2){
        try{
            Double.parseDouble(text1);
            Double.parseDouble(text2);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
