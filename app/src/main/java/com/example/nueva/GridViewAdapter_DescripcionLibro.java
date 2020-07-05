package com.example.nueva;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter_DescripcionLibro extends BaseAdapter {

    List<String> lstSource;
    Context mContext;

    public GridViewAdapter_DescripcionLibro(List<String> lstSource, Context mContext) {
        this.lstSource = lstSource;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return lstSource.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView texto;

      //  if(convertView== null) {


            texto = new TextView(mContext);
                texto.setLayoutParams(new GridView.LayoutParams(315, 100));
                //texto.setGravity(Gravity.CENTER);
            texto.setPadding(5, 5, 3, 3);
            texto.setText(lstSource.get(position));
            texto.setTextColor(Color.BLACK);
            texto.setHeight(10);
            texto.setBackgroundColor(0xFAFAFAFA);








      /*  }else{
            texto = new TextView(mContext);
            texto.setText(lstSource.get(position));
        }*/

        return texto;
    }

}
