package com.example.lexa.alcho.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.singlton.ListAlcoBase;
import com.example.lexa.alcho.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 11.01.2016.
 */
public class MyDialogListAlcoBase extends DialogFragment {
    static AlertDialog.Builder builder;
     public ArrayList<String> ras2 = new ArrayList<>();
    static public ArrayList<String> rasA = new ArrayList<>();
    public List<VvodAlchoBase> values = null;
    static ListAlcoBase manager = ListAlcoBase.getInstance();

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

        values = manager.getList() ;
        final String [] values2 = new String [values.size()];
        final String [] values3 = new String [values.size()];

        for (int i = 0; i< values.size();i++){
            StringBuilder str = new StringBuilder();
            str.append(getResources().getString(R.string.Re));
            str.append(" ");
            str.append(values.get(i).getName());
            str.append("\n");
            str.append(getResources().getString(R.string.Re2));
            str.append(" ");
            str.append(values.get(i).getName1());
            str.append("\n");
            str.append(getResources().getString(R.string.Re3));
            str.append(" ");
            str.append(values.get(i).getDegrees());
            str.append(" ");
            str.append(getResources().getString(R.string.Te10));
            str.append("\n");
            str.append(getResources().getString(R.string.Re4));
            str.append(" ");
            str.append(values.get(i).getColich());
            str.append(" ");
            str.append(getResources().getString(R.string.Te8));
            values2 [i] = str.toString();
            values3[i] = values.get(i).getName();
        }

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.Name3)
                .setNegativeButton(R.string.Button7,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ras2.clear();
                                mListener.onDialogNegativeClick(MyDialogListAlcoBase.this);
                            }
                        })
                .setMultiChoiceItems(values2, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item,
                                                boolean isChecked) {
                                if (isChecked) {

                                    ras2.add(values3[item]);
                                }
                                else if (ras2.contains(values2[item])) {

                                    ras2.remove(values3[item]);
                                }

                            }
                        })
                .setPositiveButton(R.string.Delete,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                rasA.clear();
                                rasA.addAll(ras2);
                                ras2.clear();
                                mListener.onDialogPositiveClick(MyDialogListAlcoBase.this);
                            }
                        });

        return builder.create();


    }
}
