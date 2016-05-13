package com.lexa.newnewstytby.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lexa.newnewstytby.R;
import com.lexa.newnewstytby.internet_connection.Checinternet;


/**
 * Created by Lexa on 05.05.2016.
 */
public class NewsFragment extends Fragment {

    String someString;

    WebView webView;
    ProgressBar progressBar;

    Dialog dlg =null;



    private OnFragmentInteractionListener3 mListener;


    public interface OnFragmentInteractionListener3 {

        public void onFragmentInteractionEnd3();
    }


    public static NewsFragment newInstance(String someSrting) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("someSrting", someSrting);
        newsFragment.setArguments(args);
        return newsFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener3) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        someString = getArguments().getString("someSrting");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        loadWebView();
    }



    public void internetDialog(){
        dlg = new Dialog(this.getActivity());
        dlg.setContentView(R.layout.dialog_internet);
        dlg.setTitle(getResources().getString(R.string.DialogName));
        dlg.setCancelable(false);

        Button btn1 = (Button) dlg.findViewById(R.id.button);
        Button btn2 = (Button) dlg.findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                loadWebView();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                mListener.onFragmentInteractionEnd3();
            }
        });
        dlg.show();
        ((TextView) dlg.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
    }


    public void loadWebView(){

        if (Checinternet.checkInternet(getActivity())) {

            isWebView();

        } else{
            internetDialog();
        }
    }



    public void isWebView(){
        webView = (WebView)getActivity().findViewById(R.id.webview);
        webView.setWebViewClient(new HelloWebViewClient());

        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        progressBar.setMax(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
            webView.loadUrl(someString);
        NewsFragment.this.progressBar.setProgress(0);


    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            NewsFragment.this.setValue(30);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            NewsFragment.this.setValue(100);
        }

    }


    public void setValue(int progress) {
        this.progressBar.setProgress(progress);
    }
}
