package com.example.lexa.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  implements View.OnClickListener{
    TextView text;
    TextView text1;
    static Manager manager = Manager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manager.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button =(Button)findViewById(R.id.button);
        text = (TextView)findViewById(R.id.textView2);
        text1 = (TextView)findViewById(R.id.textView3);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String se = (String)text.getText();
                text.setText(text1.getText());
                text1.setText(se);
            }
        });
        text.setOnClickListener(this);

    }
    public void clickText(View v){
        String se = (String)text.getText();
        text.setText(text1.getText());
        text1.setText(se);
    }
    @Override
    public void onClick(View v) {
        String se = (String)text.getText();
        text.setText(text1.getText());
        text1.setText(se);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button button2 =(Button)findViewById(R.id.button2);
        final EditText editText = (EditText)findViewById(R.id.editText);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texte = editText.getText().toString();
                if(TextUtils.isEmpty(texte)){
                Toast.makeText(MainActivity.this, "Vvedite ot l do 5", Toast.LENGTH_LONG).show();}
                else{
                 TextView text3 = (TextView)findViewById(R.id.textView);
                    String sere = String.valueOf(manager.getGood().get(Integer.parseInt(texte)-1));
                  text3.setText(sere);

                }
            }
        });
    }
}
