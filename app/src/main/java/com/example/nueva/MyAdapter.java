package com.example.nueva;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nueva.model.anuncio;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    String data1[], data2[], data3[];
    List<String> data11;
    List<anuncio> data;
    Context context;


    public MyAdapter(Context ct, List<anuncio> s1){
        context = ct;
        //data1 = s1;
        // data2 = s2;
        // data3 = s3;
        // data11 = s1;
        data = s1;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
       // holder.myText1.setText(data.get(position).getNombre());
      //  holder.myText2.setText(data.get(position).getNombre());
        // holder.imageView.
        Picasso.get().load(data.get(position).getUrl()).into(holder.imageView);
    }

    public String getItemTime(int position){
        //position = position -1;
        return data.get(position).getTiempo();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{


      //  TextView myText1, myText2;
        ImageView imageView;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
        //    myText1 = itemView.findViewById(R.id.myText1);
         //   myText2 = itemView.findViewById(R.id.myText2);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}

