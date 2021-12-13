package com.example.wavelength;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private TextView roomName;
    private RecyclerView recyclerview2;
    private Context context;
    Integer[] BusLibImages ={R.drawable.business_library1, R.drawable.business_library2, R.drawable.business_library3, R.drawable.business_library4, R.drawable.business_library5};
    String[] BusinessLibrary = {
            "Businiess Library, Bus2111",
            "Businiess Library, Bus2135",
            "Businiess Library, Bus2210D",
            "Businiess Library, Bus3210C",
            "Businiess Library, Bus3210G"
    };

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        recyclerview2 = getView().findViewById(R.id.recyclerview2);
//
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        recyclerview2.setLayoutManager(layoutManager2);
//        //getView().findViewById(R.id.recyclerview2);
//        RoomAdapter adapter2 = new RoomAdapter(BusLibImages, BusinessLibrary);
//        recyclerview2.setAdapter(adapter2);

        return inflater.inflate(R.layout.fragment_home, container, false);


    }



}