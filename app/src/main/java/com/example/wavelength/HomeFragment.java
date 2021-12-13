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
    Integer[] imageId;
    String[] imagesName;
    TextView libName;

    Integer[] MemLibImages ={R.drawable.memorial_library1, R.drawable.memorial_library2, R.drawable.memorial_library3, R.drawable.memorial_library4, R.drawable.memorial_library5};
    String[] MemorialLibrary = {
            "Memorial Library, Mem379",
            "Memorial Library, Mem477",
            "Memorial Library, Mem479",
            "Memorial Library, Mem777",
            "Memorial Library, Mem779"
    };

    Integer[] ColLibImages ={R.drawable.college_library1, R.drawable.college_library2, R.drawable.college_library3, R.drawable.college_library4, R.drawable.college_library5};
    String[] CollegeLibrary = {
            "College Library, Col2203",
            "College Library, Col2205",
            "College Library, Col2217",
            "College Library, Col2258",
            "College Library, Col3203"
    };

    Integer[] SteLibImages ={R.drawable.steenbock_library1, R.drawable.steenbock_library2, R.drawable.steenbock_library3, R.drawable.steenbock_library4, R.drawable.steenbock_library5};
    String[] SteenbockLibrary = {
            "Steenbock Library, Ste101",
            "Steenbock Library, Ste103",
            "Steenbock Library, Ste108",
            "Steenbock Library, Ste111",
            "Steenbock Library, Ste115"
    };

    Integer[] BusLibImages ={R.drawable.business_library1, R.drawable.business_library2, R.drawable.business_library3, R.drawable.business_library4, R.drawable.business_library5};
    String[] BusinessLibrary = {
            "Business Library, Bus2111",
            "Business Library, Bus2135",
            "Business Library, Bus2210D",
            "Business Library, Bus3210C",
            "Business Library, Bus3210G"
    };

    Integer[] SocLibImages ={R.drawable.socialwork_library1, R.drawable.socialwork_library2};
    String[] SocialWorkLibrary = {
            "Social Work Library, Soc142",
            "Social Work Library, Soc143",
    };

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerview2 = (RecyclerView) getView().findViewById(R.id.recyclerview2);
        recyclerview2.setLayoutManager(layoutManager2);
        RoomAdapter adapter2;
        libName = (TextView) getView().findViewById(R.id.location);
        if(MapsFragment.closestLib.equals("Business Library")){
            libName.setText("Closest Library: Business Library");
             adapter2 = new RoomAdapter(BusLibImages, BusinessLibrary);
        }
        else if(MapsFragment.closestLib.equals("Steenbock Library")){
            libName.setText("Closest Library: Steenbock Library");
            adapter2 = new RoomAdapter(SteLibImages, SteenbockLibrary);
        }
        else if(MapsFragment.closestLib.equals("College Library")){
            libName.setText("Closest Library: College Library");
            adapter2 = new RoomAdapter(ColLibImages, CollegeLibrary);
        }
        else if(MapsFragment.closestLib.equals("Memorial Library")){
            libName.setText("Closest Library: Memorial Library");
            adapter2 = new RoomAdapter(MemLibImages, MemorialLibrary);
        }
        else{
            libName.setText("Closest Library: Social Work Library");
            adapter2 = new RoomAdapter(SocLibImages, SocialWorkLibrary);
        }

        recyclerview2.setAdapter(adapter2);
    }



}