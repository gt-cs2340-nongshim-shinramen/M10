package com.example.m6.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

<<<<<<< HEAD

/**
 * Alert Window Class
=======
import java.util.Objects;

/**
 *
>>>>>>> 4d4cc55352e26089eaa8c6c534b2a74250855a82
 */
public class AlertWindow extends AppCompatDialogFragment {
    @SuppressWarnings("NullableProblems")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
<<<<<<< HEAD
        //noinspection ConstantConditions
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //noinspection ChainedMethodCall,ChainedMethodCall
=======
        AlertDialog.Builder builder
                = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
>>>>>>> 4d4cc55352e26089eaa8c6c534b2a74250855a82
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
