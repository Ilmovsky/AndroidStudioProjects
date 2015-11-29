package com.test.myapplication.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.test.myapplication.R;

public class SecActivity extends Activity{

    public static final String KEY_TEXT = "key_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        final TextView textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();

        String text = intent.getStringExtra(KEY_TEXT);

        if(!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }




    }
}
