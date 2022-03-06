package com.example.wattertapp.Activitys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.wattertapp.R;

public class PopUpGrifo extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_pop_up_grifo,null);

        builder.setView(view)
                .setTitle("PopUp")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }
                ).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                private TextView ubicaciontv,presiontv;
//                ubicaciontv = (TextView) findViewById(R.id.ubicationgrifotv);
//                presiontv = (TextView) findViewById(R.id.presiongrifotv);
            }
        });

        return null; // eso no va
    }
}