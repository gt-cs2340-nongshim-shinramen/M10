package com.example.m6.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.m6.R;

import java.util.Objects;

/**
 *
 */
@SuppressWarnings("ALL")
public class refuelDialog extends AppCompatDialogFragment {
    // --Commented out by Inspection (4/4/2019 1:58 PM):int fuel;

    public interface refuelDialogListener{
//        void onInputData(String input);

        /**
         *
         * @param fuel fuel amount being refueled
         */
        void buyFuel(String fuel);
    }

    private refuelDialogListener call;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            call = (refuelDialog.refuelDialogListener) activity;
        } catch(ClassCastException e) {
            Log.d("test", "activity doesn't implement");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.buy_dialog, null);
        final EditText input = view.findViewById(R.id.buy_input);
        builder.setView(view)
                .setTitle("Enter the amount of fuel")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = input.getText().toString();
//                        call.onInputData(inputStr);
                        call.buyFuel(inputStr);

                    }
                });


        return builder.create();
    }
}
