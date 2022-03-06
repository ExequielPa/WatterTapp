package com.example.wattertapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattertapp.Model.Camion;

import java.util.List;

public class AdapterCamion extends RecyclerView.Adapter<AdapterCamion.ViewHolder> {
    LayoutInflater inflater;
    List<Camion> camions;

    public AdapterCamion(Context ctx, List<Camion> camions){
        this.inflater = LayoutInflater.from(ctx);
        this.camions = camions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return camions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
