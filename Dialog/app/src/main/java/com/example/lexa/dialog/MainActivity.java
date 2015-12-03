package com.example.lexa.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements MyDialog.NoticeDialogListener {

    TextView textView1 = null;
    TextView textView2 = null;
     TextView textView =  null;
    static final int VI = 1;
    static final int VI2 = 2;
    static final int VI3 = 3;
    int viv = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView2);
         textView2 = (TextView)findViewById(R.id.textView3);
         textView = (TextView)findViewById(R.id.textView);
        final Button button = (Button)findViewById(R.id.button);
        final DialogFragment myDialog = new MyDialog();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.show(getFragmentManager(), "myDialog");
                viv = VI;
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.show(getFragmentManager(),"myDialog1");
                viv = VI2;
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                myDialog.show(getFragmentManager(),"myDialog2");
               viv = VI3;

           }
        });

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, Activity2.class);
                 startActivity(intent);
             }
         });





    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        String nas1 = MyDialog.ras.get(0);
        for(int i = 1; i<MyDialog.ras.size();i++) {
            nas1 = nas1 +"\n"+ MyDialog.ras.get(i);
        }
        switch(viv) {
            case VI2:
            textView1.setText(nas1);
                break;
            case VI:
            textView.setText(nas1);
                break;
            case VI3:
            textView2.setText(nas1);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}







