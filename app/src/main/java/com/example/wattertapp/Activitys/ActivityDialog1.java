package com.example.wattertapp.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.wattertapp.R;

public class ActivityDialog1 extends AppCompatDialogFragment {

    Custom_DialogInterfaceX dialogInterfaceX;
    EditText EDT1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_dialog1,null);

        EDT1 = view.findViewById(R.id.txtrut);

        builder.setView(view).setTitle("Custom Dialog")
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text1 = EDT1.getText().toString();

                dialogInterfaceX.applyTexts(text1);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        dialogInterfaceX = (Custom_DialogInterfaceX) context;
    }

    public interface Custom_DialogInterfaceX{
        void applyTexts(String EDT1);
    }
}