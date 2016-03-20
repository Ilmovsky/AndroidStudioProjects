package com.example.lexa.alcho.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;

import com.example.lexa.alcho.construct.AlcoBase;
import com.example.lexa.alcho.ListAlcho;
import com.example.lexa.alcho.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 29.12.2015.
 */
public class MyDialogListAlco extends DialogFragment {
    static AlertDialog.Builder builder;
    static public ArrayList<String> ras1 = new ArrayList<>();
    static public ArrayList<String> ras = new ArrayList<>();
    public List<AlcoBase> values = null;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(android.app.DialogFragment dialog);
        public void onDialogNegativeClick(android.app.DialogFragment dialog);


    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public AlertDialog onCreateDialog(Bundle savedInstanceState) {

         values = ListAlcho.datasource.getAllAlcoBase() ;
        final String [] values2 = new String [values.size()];
        for (int i = 0; i< values.size();i++){
              values2[i] = values.get(i).getName();
        }

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.Name)
                .setNegativeButton(R.string.Button7,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ras1.clear();
                                mListener.onDialogNegativeClick(MyDialogListAlco.this);
                            }
                        })
                .setMultiChoiceItems(values2, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item,
                                                boolean isChecked) {
                                if (isChecked) {

                                    ras1.add(values2[item]);
                                }
                                else if (ras1.contains(values2[item])) {

                                   ras1.remove(values2[item]);
                                }

                            }
                        })
                .setPositiveButton(R.string.Button10,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ras.clear();
                                ras.addAll(ras1);
                                ras1.clear();
                                mListener.onDialogPositiveClick(MyDialogListAlco.this);
                            }
                        });

        return builder.create();


    }

}
