package com.example.lexa.alcho.fragm_inform;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lexa.alcho.Inform;
import com.example.lexa.alcho.R;

import java.lang.reflect.Field;


/**
 * Created by Lexa on 23.12.2015.
 */
public class Fragment1 extends Fragment {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, null);

        ImageView imageView = (ImageView)v.findViewById(R.id.image);

        int drawableId = 0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(Inform.text2);
            drawableId  = field.getInt(null);

        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        imageView.setImageResource(drawableId);


        TextView textView = (TextView)v.findViewById(R.id.textView);
        textView.setText(Inform.text);

        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(Inform.text1);

        return v;

    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }


    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {

        }
    }
}
