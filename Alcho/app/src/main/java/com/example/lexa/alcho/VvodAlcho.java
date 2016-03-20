package com.example.lexa.alcho;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lexa.alcho.construct.AlcoBase;
import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.singlton.ListAlcoBase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lexa on 25.12.2015.
 */
public class VvodAlcho  extends AppCompatActivity {

    public static final String KEY_TEXT1 = "key_text";
    int i = 0;
    List<AlcoBase> values = null;
    static public String text = null;
    static public String text2 = null;
    static public String text3 = null;

    static ListAlcoBase manager = ListAlcoBase.getInstance();
    static public VvodAlchoBase list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vvod_alcho);

        ListAlcho.datasource.open();
        values = ListAlcho.datasource.getAllAlcoBase();

        Intent intent = getIntent();
        i = intent.getIntExtra(KEY_TEXT1, 1);

        text =  values.get(i).getName();
        final String text21 = values.get(i).getDegrees();
        String text22 = getResources().getString(R.string.Te5);
        String text23 = getResources().getString(R.string.Te10);
        text2 = text22 + " " + text21 + " " + text23;
        text3 = values.get(i).getImage();

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(text);
        ImageView imageView = (ImageView)findViewById(R.id.image);

        int drawableId = 0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(text3);
            drawableId  = field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        imageView.setImageResource(drawableId);

        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText(text2);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final EditText editText2 = (EditText)findViewById(R.id.editText2);
        final EditText editText3 = (EditText)findViewById(R.id.editText3);

        final Button button = (Button)findViewById(R.id.button);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = new VvodAlchoBase();
                list.setName(text);
                if (editText.getText().length() == 0) {
                    Toast.makeText(VvodAlcho.this, R.string.Text2, Toast.LENGTH_LONG).show();
                    imm.hideSoftInputFromWindow(button.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    list.setName1(editText.getText().toString());

                    if (editText2.getText().length() == 0) {
                        Pattern p = Pattern.compile("\\d+\\W\\d");
                        Matcher m = p.matcher(text21);
                        double res = 0;
                        double res1 = 0;
                        int re = 0;
                        while (m.find()){
                            res = Double.parseDouble(m.group().toString());
                            res1 +=res;
                            re++;
                        }
                        res1 = res1/re;
                        list.setDegrees(res1);
                    }
                    else {
                        list.setDegrees(Double.parseDouble(editText2.getText().toString()));}

                        if (editText3.getText().length() == 0) {
                            Toast.makeText(VvodAlcho.this, R.string.Text3, Toast.LENGTH_LONG).show();
                            imm.hideSoftInputFromWindow(button.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        } else {
                            list.setColich(Integer.parseInt(editText3.getText().toString()));
                            list.setId(234);
                            list.setIdAll(235);
                            manager.add();
                            Intent intent = new Intent(VvodAlcho.this, Novoe.class);
                            startActivity(intent);
                        }
                }

            }
        });

    }

    @Override
    protected void onPause() {
        ListAlcho.datasource.close();
        super.onPause();
    }

}