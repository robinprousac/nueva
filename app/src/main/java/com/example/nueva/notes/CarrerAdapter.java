package com.example.nueva.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nueva.R;

import java.util.ArrayList;
import java.util.List;

public class CarrerAdapter extends ArrayAdapter {
    public CarrerAdapter(@NonNull Context context, ArrayList<CarrerItems> carrerList) {
        super(context, 0, carrerList);
    }



}
