package com.lexa.belhard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Lexa on 25.05.2016.
 */
public class FragmentImoji extends Fragment {

    private InputMethodManager inputManager;

    EditText editText;
    ImageButton button1;
    ImageButton button2;
    Boolean isKeyboard = false;
    Boolean isImoji = false;

    private OnFragmentInteractionListener2 mListener;

    public interface OnFragmentInteractionListener2 {
        void onFragmentInteraction2 (String text);
        void onFragmentInteraction3 ();
        void onFragmentInteraction4 ();
    }


    public static FragmentImoji newInstance() {
        FragmentImoji listFragment = new FragmentImoji();
        return listFragment;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }

    public void addImoji(int imoji){
        String textIs = editText.getText().toString();
        editText.setText(textIs + " " + getEmijoByUnicode(imoji));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imoji_fragment, container, false);
        inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        button1 = (ImageButton)getActivity().findViewById(R.id.btn_emo);
        button2 = (ImageButton)getActivity().findViewById(R.id.btn_send);
        editText = (EditText)getActivity().findViewById(R.id.inputText);
        button2.setSelected(true);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!isKeyboard) {
                    isKeyboard = true;
                }
                if (isImoji) {
                    isImoji = false;
                    mListener.onFragmentInteraction4();
                    button2.setSelected(true);
                    button1.setSelected(false);
                }
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isKeyboard) {
                    isKeyboard = true;
                }
                if (isImoji) {
                    isImoji = false;
                    mListener.onFragmentInteraction4();
                    button2.setSelected(true);
                    button1.setSelected(false);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isKeyboard) {
                    isKeyboard = false;
                inputManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}
                button2.setSelected(false);
                button1.setSelected(true);
                if(!isImoji) {
                    isImoji = true;
                    mListener.onFragmentInteraction3();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isKeyboard) {
                    isKeyboard = false;
                    inputManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                if (isImoji) {
                    isImoji = false;
                    mListener.onFragmentInteraction4();
                }
                mListener.onFragmentInteraction2(editText.getText().toString());
            }
        });
    }


    @Override
    public void onPause()
    {
        super.onPause();
    }



    @Override
    public void onResume()
    {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }


}
