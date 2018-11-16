package com.lequiz.practice.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Spinner;
import android.widget.Toast;
import com.lequiz.practice.R;


public class ProfileAccountEdit extends android.support.v4.app.DialogFragment {

    private Spinner spinner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.ui_profile_edit_account_details,null));


        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Canceled... ",Toast.LENGTH_LONG).show();
            }
        });


        AlertDialog alert = builder.create();

        spinner = alert.findViewById(R.id.profile_country_selector);



        return builder.create();
    }
}
