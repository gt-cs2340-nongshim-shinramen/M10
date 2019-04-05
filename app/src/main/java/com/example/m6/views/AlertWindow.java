package com.example.m6.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import java.util.Objects;

/**
 *
 */
public class AlertWindow extends AppCompatDialogFragment {
    @SuppressWarnings("NullableProblems")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //noinspection ConstantConditions
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //noinspection ChainedMethodCall,ChainedMethodCall
        builder.setTitle("NOTICE!!")
                .setMessage("this item is not available in this planet")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();


    }
}
