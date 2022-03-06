package com.example.wattertapp.Activitys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.wattertapp.R;

public class ActivityDialog extends AppCompatDialogFragment {

    Custom_DialogInterface dialogInterface;
    EditText EDT1,EDT2;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_dialog,null);

        EDT1 = view.findViewById(R.id.txtmensaje);
        EDT2 = view.findViewById(R.id.txtmensaje2);

        builder.setView(view).setTitle("CustomDialog")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
        })
                .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text1 = EDT1.getText().toString();
                String text2 = EDT2.getText().toString();

                dialogInterface.applyTexts(text1,text2);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        dialogInterface = (Custom_DialogInterface) context;
    }

    public interface Custom_DialogInterface{
        void applyTexts(String EDT1, String EDT2);
    }
}