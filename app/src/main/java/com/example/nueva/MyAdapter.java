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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    String data1[], data2[], data3[];
    List<String> data11;
    List<anuncio> data;
    Context context;
    int position_aux;
    anuncio aux;

    public MyAdapter(Context ct, List<anuncio> s1){
        context = ct;
        //data1 = s1;
        // data2 = s2;
        // data3 = s3;
        // data11 = s1;
        data = s1;
        position_aux = 0;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        aux = data.get(position_aux);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(Integer.parseInt(aux.getTipo() )== 2){
            view = inflater.inflate(R.layout.my_row2, parent, false);

        }else{
            view = inflater.inflate(R.layout.my_row, parent, false);
        }





        return new MyviewHolder(view, Integer.parseInt(aux.getTipo() ));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
       // holder.myText1.setText(data.get(position).getNombre());
      //  holder.myText2.setText(data.get(position).getNombre());
        // holder.imageView.

        aux = data.get(position);
        if(Integer.parseInt(aux.getTipo() ) == 2){
            //holder.myText1.setText(data.get(position).getNombre());


            //getLifecycle().addObserver(holder.youTubePlayerView );


            holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = aux.getNombre();
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        }else{
            Picasso.get().load(data.get(position).getUrl()).into(holder.imageView);
        }


        if((position_aux+1)<data.size()){
           // position_aux++;
            position_aux = position + 1;
        }else{
            position_aux = 0;
        }

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


        TextView myText1;
        ImageView imageView;
        YouTubePlayerView youTubePlayerView;
        public MyviewHolder(@NonNull View itemView, int type) {
            super(itemView);
        //    myText1 = itemView.findViewById(R.id.myText1);
         //   myText2 = itemView.findViewById(R.id.myText2);
            if(type == 2){
               // myText1 = itemView.findViewById(R.id.textView_test);
                 youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);



            }else {
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}

