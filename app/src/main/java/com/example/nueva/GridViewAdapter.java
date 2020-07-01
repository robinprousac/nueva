package com.example.nueva;

import android.content.Context;
import android.graphics.Color;
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
            texto.setLayoutParams(new GridView.LayoutParams(250, 80));
            texto.setPadding(5, 5, 3, 3);
            texto.setText(lstSource.get(position));
            texto.setTextColor(Color.BLACK);
            texto.setHeight(10);
            texto.setBackgroundColor(0xFFFFFFFF);


         /*   if(bandera){
                texto.setBackgroundColor(0xFF0000FF);
            }else{
                texto.setBackgroundColor(0x00FFFFFF);
            }

            if(cont == 6){
                bandera =true;

            }else if(cont == 12){
                bandera = false;
                cont=0;
        }else{
                cont++;
            }
*/

           /* int num = 0;

            num = position%6;

            if(num ==0){

                bandera=true;
                cont = 0;
            }else{

                if(cont>6){
                    bandera=false;
                }


            }*/





      /*  }else{
            texto = new TextView(mContext);
            texto.setText(lstSource.get(position));
        }*/

        return texto;
    }
}
