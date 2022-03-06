package com.example.wattertapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wattertapp.Model.list_element;
import com.example.wattertapp.R;

import java.util.List;

public class ListAadapter extends RecyclerView.Adapter<ListAadapter.ViewHolder> {

    private List<list_element> mData;
    private final LayoutInflater mInflater;
    private final Context context;
    final ListAadapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void  onItemClick(list_element item);
    }

    public ListAadapter(List<list_element> itemList, Context context, ListAadapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ListAadapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_element,parent,false);
        return new ListAadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAadapter.ViewHolder holder,final int position){
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transittion));
        holder.bindData(mData.get(position));
    }

    public void setItems(List<list_element> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImagen;
        TextView name, city,status;
        CardView cv;

        ViewHolder(View itemView){
            super(itemView);
            iconImagen = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            status = itemView.findViewById(R.id.statusTextVew);
            cv = itemView.findViewById(R.id.cv);
        }

        void bindData(final list_element item){
            iconImagen.setColorFilter(Color.parseColor(item.getColo()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            city.setText(item.getCity());
            status.setText(item.getStatus());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
