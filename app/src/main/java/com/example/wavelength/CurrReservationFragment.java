package com.example.wavelength;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wavelength.placeholder.PlaceholderContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CurrReservationFragment extends Fragment {

    private FirebaseAuth auth2;
    private static ArrayList<Reservation> res2 = new ArrayList<>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CurrReservationFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CurrReservationFragment newInstance(int columnCount) {
        CurrReservationFragment fragment = new CurrReservationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth2 = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        Context context = this.getContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(auth2,sqLiteDatabase);
//        dbHelper.onAddData(user.getEmail().toString(), "Grainger", "001","5:00","5:30","2/10/2021");
//        dbHelper.onAddData(user.getEmail().toString(),"College Library", "001","5:00","5:30","2/10/2021");
        res2 = dbHelper.readNotes(user.getEmail().toString(),true);
        for (Reservation note : res2){
            Log.d("here",note.getLibraryName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curr_reservation_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            Reservation title = new Reservation("Your Current Reservations", "Your Current Reservations", "        Your Current Reservations", "Your Current Reservations", "Your Current Reservations", "Your Current Reservations");
            res2.add(0, title);
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter2(res2));
        }
        return view;
    }
}