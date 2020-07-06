package com.example.nueva;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nueva.model.autoridad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Auth extends RecyclerView.Adapter<Adapter_Auth.MyViewHolder> {


    Context context;
    List<autoridad> auts;

    public Adapter_Auth(Context ct, List<autoridad> au){

        context = ct;
        auts = au;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrowauth, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mytext1.setText(auts.get(position).getCargo());
        holder.mytext2.setText(auts.get(position).getNombres());
        holder.mytext3.setText(auts.get(position).getPeriodo());
        Picasso.get().load(auts.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return auts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytext1 , mytext2, mytext3;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mytext1 = itemView.findViewById(R.id.textView10);
            mytext2 = itemView.findViewById(R.id.textView12);
            mytext3 = itemView.findViewById(R.id.textView13);
            imageView = itemView.findViewById(R.id.imageView10);
        }
    }


}