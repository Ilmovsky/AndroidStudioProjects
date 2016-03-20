package com.example.lexa.alcho;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lexa.alcho.basehelper.DataSour;
import com.example.lexa.alcho.construct.BigBase;
import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.dialog.MyDialogListAlcoBase;
import com.example.lexa.alcho.singlton.ListAlcoBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lexa on 21.12.2015.
 */
public class Novoe extends AppCompatActivity implements MyDialogListAlcoBase.NoticeDialogListener, View.OnClickListener {

    public static DataSour datasource;
    List<VvodAlchoBase> values = null;
    List<BigBase> values1 = null;
    static ListAlcoBase manager = ListAlcoBase.getInstance();
    static String late = null;
    VvodAlchoBase alco = null;
    Dialog dlg = null;
   DialogFragment myDialogListAlco = new MyDialogListAlcoBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novoe);

        datasource = new DataSour(this);

        final Button button4 = (Button)findViewById(R.id.button4);
        values = manager.getList();
        button4.setOnClickListener(this);


        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Novoe.this, MapNow.class);
                startActivity(intent);
            }
        });


        final Button button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Novoe.this, ListAlcho.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        datasource.open();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        final String date = dateFormat.format(new Date());
        if (manager.getList().size() != 0) {

            dlg = new Dialog(Novoe.this);
            dlg.setContentView(R.layout.dialog_add_base);
            dlg.setTitle(R.string.Name2);

            dlg.setCancelable(true);

            final EditText userInput = (EditText) dlg.findViewById(R.id.input_text);

            final Button btn = (Button)dlg.findViewById(R.id.button5);
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imm.hideSoftInputFromWindow(btn.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    myDialogListAlco.show(getFragmentManager(), "myDialogListAlcoBase");
                }
            });

            final Button btn2 = (Button)dlg.findViewById(R.id.button6);

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (manager.getList().size() == 0) {
                        Toast.makeText(Novoe.this, R.string.Te11, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Novoe.this, MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        values = manager.getList();
                        if (userInput.getText().length() == 0) {
                            late = date + "." + values.get(0).getName();
                        } else {
                            late = date + "." + userInput.getText().toString();
                        }
                        datasource.createAlcoBase(date, late);

                        values1 = datasource.getAllAlcoBase();

                        long i2 = values1.get(0).getId();

                        for (int nextInt = 0; nextInt < manager.getList().size(); nextInt++) {
                            String name = values.get(nextInt).getName();
                            String name1 = values.get(nextInt).getName1();
                            double degree = values.get(nextInt).getDegrees();
                            int colich = values.get(nextInt).getColich();

                            datasource.createAlcoBaseA(name, name1, degree, colich, i2);
                        }
                        manager.clear();

                        Intent intent = new Intent(Novoe.this, MainActivity.class);
                        startActivity(intent);

                        datasource.close();
                        userInput.getText().clear();
                        Novoe.this.dlg.dismiss();
                    }
                }
            });

            Button btn3 = (Button)dlg.findViewById(R.id.button7);

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userInput.getText().clear();
                    Intent intent = new Intent(Novoe.this, MainActivity.class);
                    startActivity(intent);
                    Novoe.this.dlg.dismiss();
                }
            });

            dlg.show();
            ((TextView)dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        }
        else {

            Intent intent = new Intent(Novoe.this, MainActivity.class);
            startActivity(intent);

            datasource.close();
        }
        return;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

            for (int i = 0; i < MyDialogListAlcoBase.rasA.size(); i++) {
                for (int j = 0; j < values.size(); j++) {
                    if (MyDialogListAlcoBase.rasA.get(i).equalsIgnoreCase(values.get(j).getName())) {
                        alco = values.get(j);
                        values.remove(alco);

                    }
                }
            }
        }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }
}

