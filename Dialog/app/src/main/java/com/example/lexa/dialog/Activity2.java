package com.example.lexa.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Lexa on 03.12.2015.
 */
public class Activity2 extends Activity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        final Context context = this;
         Button button;
         final TextView final_text;

        button = (Button) findViewById(R.id.prompt_button);
        Button button2 = (Button) findViewById(R.id.button2);
        final_text = (TextView) findViewById(R.id.final_text);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);


                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);


                mDialogBuilder.setView(promptsView);


                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);


                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Вводим текст и отображаем в строке ввода на основном экране:
                                        final_text.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alertDialog = mDialogBuilder.create();


                alertDialog.show();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });
    }
}
