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

public class GridViewAdapter extends BaseAdapter {

    List<String> lstSource;
    Context mContext;

    public GridViewAdapter(List<String> lstSource, Context mContext) {
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

    boolean bandera = false;
    int cont = 0;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView texto;

      //  if(convertView== null) {


            texto = new TextView(mContext);
            if(isNumeric(lstSource.get(position))||(lstSource.get(position)).equals("No.")||(lstSource.get(position)).equals("NOTA")){
                texto.setLayoutParams(new GridView.LayoutParams(174, 85));
                texto.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if((lstSource.get(position)).length()>7||(lstSource.get(position)).equals("CURSO")||(lstSource.get(position)).equals("FECHA")){
                texto.setLayoutParams(new GridView.LayoutParams(174, 85));
                texto.setGravity(Gravity.CENTER_HORIZONTAL);
            }else {
                texto.setLayoutParams(new GridView.LayoutParams(174, 85));
                texto.setGravity(Gravity.CENTER_HORIZONTAL);
            }

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

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
