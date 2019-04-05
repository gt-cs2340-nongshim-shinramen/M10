package com.example.m6.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.m6.R;

<<<<<<< HEAD
@SuppressWarnings("ALL")
=======
import java.util.Objects;

/**
 *
 */
>>>>>>> 4d4cc55352e26089eaa8c6c534b2a74250855a82
public class BuyDialog extends AppCompatDialogFragment {

    private int price;
    private String goodsType;

    /**
     *
     */
    public interface BuyDialogListener{
        /**
         *
         * @param input the name of goods
         */
        void onInputData(String input);

        /**
         *
         * @param goods name of the goods
         * @param price price of the goods
         */
        void buyItem(String goods, int price);
    }

    private BuyDialogListener call;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            call = (BuyDialogListener) activity;
        } catch(ClassCastException e) {
            Log.d("test", "activity doesn't implement");
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            price = bundle.getInt("price");
            goodsType = bundle.getString("goodsType");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
<<<<<<< HEAD
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.buy_dialog, null);
        final EditText input = (EditText)view.findViewById(R.id.buy_input);
=======
        View view = inflater.inflate(R.layout.buy_dialog, null);
        final EditText input = view.findViewById(R.id.buy_input);
>>>>>>> 4d4cc55352e26089eaa8c6c534b2a74250855a82
        builder.setView(view)
                .setTitle("Enter the amount of items")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         String inputStr = input.getText().toString();
                         call.onInputData(inputStr);
                         call.buyItem(goodsType, price);
                        dismiss();
                    }
                });


        return builder.create();
    }
}
