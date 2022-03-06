package com.example.wattertapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattertapp.Activitys.DescriptionActivity;
import com.example.wattertapp.Model.EstadoEmerg;
import com.example.wattertapp.R;

import java.util.List;

public class AdapterEstadoEmerg extends RecyclerView.Adapter<AdapterEstadoEmerg.ViewHolder>{

    LayoutInflater inflater;
    List<EstadoEmerg> estadoemerg;
    Context context;

    public AdapterEstadoEmerg(Context ctx, List<EstadoEmerg> estadoemerg){
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.estadoemerg = estadoemerg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_list_element,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = position;
        holder.id_estado_eme.setText(estadoemerg.get(position).getId_estado_eme());
        holder.estado_emergencia.setText(estadoemerg.get(position).getEstado_emergencia());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("emergenciasEst",estadoemerg.get(pos));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return estadoemerg.size();  }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_estado_eme,estado_emergencia,descricpicion_emergencia;
        CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_estado_eme = itemView.findViewById(R.id.nameTextView);
            estado_emergencia = itemView.findViewById(R.id.cityTextView);
        }
    }
}
