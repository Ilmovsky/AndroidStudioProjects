package com.example.lexa.alcho;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lexa.alcho.adapters.RecycleAdapt;
import com.example.lexa.alcho.construct.AlcoBase;
import com.example.lexa.alcho.basehelper.DataSource;
import com.example.lexa.alcho.dialog.MyDialogListAlco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 21.12.2015.
 */
public class ListAlcho extends AppCompatActivity implements MyDialogListAlco.NoticeDialogListener, SearchView.OnQueryTextListener {
    public static DataSource datasource;
    AlcoBase alco = null;

    List<AlcoBase> values = null;
    private RecyclerView mRecyclerView;
    private RecycleAdapt mRecycleAdaper;
    Dialog dlg = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_alcho);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        datasource = new DataSource(this);
        datasource.open();

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

        values = datasource.getAllAlcoBase();

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);

        mRecycleAdaper = new RecycleAdapt(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        final String[] names = getResources().getStringArray(R.array.listArray);
        final String[] degree = getResources().getStringArray(R.array.listValues);
        final String [] im = getResources().getStringArray(R.array.listImages);
        final String[] site = getResources().getStringArray(R.array.listValues1);
        iii:for (int nextInt = 0; nextInt <names.length;nextInt++){
            for(int i =0;i<values.size();i++) {
                if (values.get(i).getName().equals(names[nextInt]) ){
                    continue iii;
                }
            }
            alco = datasource.createAlcoBase(names[nextInt], degree[nextInt],im[nextInt],site[nextInt]);


        }
        values = datasource.getAllAlcoBase();
        mRecycleAdaper.addItems(values);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        final  DialogFragment myDialogListAlco = new MyDialogListAlco();
        final Context context = this;


        dlg = new Dialog(context);
        dlg.setContentView(R.layout.dialog_add);
        dlg.setTitle(R.string.Name1);
        dlg.setCancelable(false);

        final EditText userInput = (EditText) dlg.findViewById(R.id.input_text);
        final EditText userInput2 = (EditText) dlg.findViewById(R.id.input_text2);
        final EditText userInput3 = (EditText) dlg.findViewById(R.id.input_text3);

        final Button btn3 = (Button)dlg.findViewById(R.id.button7);
        final Button btn2 = (Button)dlg.findViewById(R.id.button6);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);



        mActionBarToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_remove:
                        myDialogListAlco.show(getFragmentManager(), "myDialogListAlco");
                        return true;
                    case R.id.action_edit:



                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                userInput.getText().clear();
                                userInput2.getText().clear();
                                userInput3.getText().clear();
                                ListAlcho.this.dlg.dismiss();
                            }
                        });


                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int ar = 0;
                                if (userInput.getText().length() == 0) {
                                    Toast.makeText(ListAlcho.this, R.string.Text, Toast.LENGTH_LONG).show();
                                    imm.hideSoftInputFromWindow(btn2.getWindowToken(),
                                            InputMethodManager.HIDE_NOT_ALWAYS);
                                } else {
                                    String rar = userInput.getText().toString().toUpperCase();
                                    String rar2 = null;
                                    String rar3 = null;
                                    String rar4 = "alco";
                                    if (userInput2.getText().length() == 0) {
                                        rar2 = "0";
                                    } else {
                                        rar2 = userInput2.getText().toString();
                                    }
                                    if (userInput3.getText().length() == 0) {
                                        rar3 = getResources().getString(R.string.Site);
                                    } else {
                                        rar3 = userInput3.getText().toString();
                                    }
                                    AlcoBase alcoBase = null;
                                    for (int i = 0; i < values.size(); i++) {
                                        if (values.get(i).getName().equals(rar)) {
                                            ar = 1;
                                            Toast.makeText(ListAlcho.this, R.string.Te9, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    if (ar == 0) {
                                        alcoBase = datasource.createAlcoBase(rar, rar2, rar4, rar3);
                                        values = datasource.getAllAlcoBase();
                                        mRecycleAdaper.addItems(values);
                                        userInput.getText().clear();
                                        userInput2.getText().clear();
                                        userInput3.getText().clear();
                                        ListAlcho.this.dlg.dismiss();
                                    }
                                }
                            }


                        });

                        dlg.show();
                        ((TextView)dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);


                return true;
            }
            return false;
        }
    });

             mRecycleAdaper.setItemClickListener(new RecycleAdapt.ItemClickListener() {
                 @Override
                 public void onClick(AlcoBase summa) {
                     Intent intent2 = new Intent(ListAlcho.this, Inform.class);
                     intent2.putExtra(Inform.KEY_TEXT, summa.getId());
                     startActivity(intent2);
                 }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_alco, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {

                        mRecycleAdaper.setFilter(values);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });

        return true;

    }


    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        if (mRecycleAdaper.getItemCount() > 0) {
            for (int i = 0; i < MyDialogListAlco.ras.size(); i++) {
                for (int j = 0; j < values.size(); j++) {
                    if (MyDialogListAlco.ras.get(i).equalsIgnoreCase(values.get(j).getName())) {
                        alco = values.get(j);
                        datasource.deleteAlcoBase(alco);
                        values.remove(alco);
                        mRecycleAdaper.addItems(values);
                    }
                }
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<AlcoBase> filteredModelList = filter(values, newText);
        mRecycleAdaper.setFilter(filteredModelList);
        return true;
    }

    private List<AlcoBase> filter(List<AlcoBase> models, String query) {
        query = query.toLowerCase();

        final List<AlcoBase> filteredModelList = new ArrayList<>();
        for (AlcoBase model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
