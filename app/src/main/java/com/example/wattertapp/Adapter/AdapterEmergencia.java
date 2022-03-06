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
import com.example.wattertapp.Model.Emergencia;
import com.example.wattertapp.R;

import java.util.List;

public class AdapterEmergencia extends RecyclerView.Adapter<AdapterEmergencia.ViewHolder>{

    LayoutInflater inflater;
    List<Emergencia> emergencias;
    Context context;
    final AdapterEmergencia.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Emergencia item);
    }

    public AdapterEmergencia(List<Emergencia> emergencias, Context ctx ,AdapterEmergencia.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.emergencias =emergencias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_list_element,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.cv.setAnimation(AnimationUtils.loadAnimation(PrincipalActivity.,R.anim.fade_transittion));
        holder.direccion.setText(emergencias.get(position).getDireccion());
        holder.codigo_categoria.setText(emergencias.get(position).getCodigo_categoria());
        holder.estado_emergencia.setText(emergencias.get(position).getEstado_emergencia());

        holder.bindData(emergencias.get(position));
    }

    @Override
    public int getItemCount() {
        return emergencias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_emergencia,descripcion_emer,direccion,id_cod_emer,codigo_categoria,nombre_categoria
                ,rut_voluntario,nombre_voluntario,apellido_voluntario,estado_emergencia,nombre_compania,cod_camion
                ,latitud,longuitud;
        CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            direccion = itemView.findViewById(R.id.nameTextView);
            codigo_categoria = itemView.findViewById(R.id.cityTextView);
            estado_emergencia = itemView.findViewById(R.id.statusTextVew);
            cv = itemView.findViewById(R.id.cv);
        }

        void bindData(final Emergencia item){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
