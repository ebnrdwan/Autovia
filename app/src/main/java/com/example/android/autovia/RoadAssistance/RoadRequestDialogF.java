package com.example.android.autovia.RoadAssistance;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.android.autovia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoadRequestDialogF extends DialogFragment {


    public RoadRequestDialogF() {
        // Required empty public constructor
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("dialg test")
                .setMessage("hey im testing my dialog")
                .setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"hey cancel",Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"hey ok",Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}
