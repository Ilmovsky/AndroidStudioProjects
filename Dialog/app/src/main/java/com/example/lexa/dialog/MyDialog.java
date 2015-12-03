package com.example.lexa.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;

public class MyDialog extends DialogFragment {
    static AlertDialog.Builder builder;
    static public ArrayList<String> ras1 = new ArrayList<>();
    static public ArrayList<String> ras = new ArrayList<>();
    static  final int IRER = 10;
    static int se = 0;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
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
        final String[] mChooseCats = getResources().getStringArray(R.array.listArray);
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите любимое имя кота")
                .setNeutralButton("Назад",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ras1.clear();
                                se = IRER;
                                mListener.onDialogNegativeClick(MyDialog.this);
                            }
                        })
                .setMultiChoiceItems(mChooseCats, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item,
                                                boolean isChecked) {
                                if (isChecked) {

                                    ras1.add(mChooseCats[item]);
                                }
                                else if (ras1.contains(mChooseCats[item])) {

                                    ras1.remove(mChooseCats[item]);
                                }

                            }
                        })
                .setPositiveButton("Bgth",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ras.clear();
                               ras.addAll(ras1);
                                ras1.clear();
                                mListener.onDialogPositiveClick(MyDialog.this);
                            }
                        });

        return builder.create();


    }

}