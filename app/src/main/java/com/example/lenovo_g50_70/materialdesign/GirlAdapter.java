package com.example.lenovo_g50_70.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo-G50-70 on 2017/3/22.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder>{
    private Context context;
    private List<Girl> girlList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView girlImage;
        TextView girlText;
        public ViewHolder(View view){
            super(view);
            cardView= (CardView) view;
            girlImage= (ImageView) view.findViewById(R.id.girl_image);
            girlText= (TextView) view.findViewById(R.id.girl_name);
        }
    }

    public GirlAdapter(List girlList){
        this.girlList=girlList;
    }

    @Override
    public GirlAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.girl_item,parent,false);
        //----------------
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Girl girl =girlList.get(position);
                Intent intent =new Intent(context,GirlActivity.class);
                intent.putExtra(GirlActivity.GIRL_NAME,girl.getName());
                intent.putExtra(GirlActivity.GIRL_IMAGE_ID,girl.getImageId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlAdapter.ViewHolder holder, int position) {
        Girl girl =girlList.get(position);
        holder.girlText.setText(girl.getName());
        Glide.with(context).load(girl.getImageId()).into(holder.girlImage);
    }

    @Override
    public int getItemCount() {
        return girlList.size();
    }
}
